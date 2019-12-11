package com.example.demo.controller;

import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SupplierDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Item;
import com.example.demo.model.Supplier;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.SupplierRepository;

@RestController
@RequestMapping("/api/v1")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping("/items")
    public List < Item > getAllItems() {
        return itemRepository.findAll();
    }

    @GetMapping("/items/{id}")
    public ResponseEntity < Item > getItemById(@PathVariable(value = "id") Long itemId)
    throws ResourceNotFoundException {
        Item item = itemRepository.findOne(itemId);
        return ResponseEntity.ok().body(item);
    }
    
    @GetMapping("/items/active")
    public List < Item > getItemsBySate(){
        List<Item> items = itemRepository.findAll().stream().filter(state -> state.getState().equals("active"))
        		.collect(Collectors.toList());
        return items;
    }
    @PostMapping("/items")
    public Item createItem(@Valid @RequestBody Item item) {
    	item.setCreationDate(new Date());
    	item.setState("active");
        return itemRepository.save(item);
    }
    @PostMapping("/itemsuser")
    public Item createItem2(
    		@RequestParam(name="itemCode") Long itemCode,
    		@RequestParam(name="description") String description, 
    		@RequestParam(name="username") String userName,
    		@RequestParam(name="price") double price) {
    	Item item = new Item();
    	item.setItemCode(itemCode);
    	item.setDescription(description);
    	item.setPrice(price);
    	item.setCreationDate(new Date());
    	item.setState("active");
    	Supplier user = supplierRepository.findByUsername(userName);
    	item.setCreator(new SupplierDTO(user));
        return itemRepository.save(item);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity < Item > updateItem(@PathVariable(value = "id") Long itemId,
        @Valid @RequestBody Item itemDetails) throws ResourceNotFoundException {
        Item item = itemRepository.findOne(itemId);
        
        if (itemDetails.getDescription() != null) item.setDescription(itemDetails.getDescription());
        if (itemDetails.getPrice() != 0) item.setPrice(itemDetails.getPrice());
        if (itemDetails.getState() != null) item.setState(itemDetails.getState());
        if (itemDetails.getSuppliers() != null) item.setSuppliers(itemDetails.getSuppliers());
        if (itemDetails.getPriceReduction() != null) item.setPriceReduction(itemDetails.getPriceReduction());
        if (itemDetails.getCreationDate() != null) item.setCreationDate(itemDetails.getCreationDate());
        if (itemDetails.getCreator() != null) item.setCreator(itemDetails.getCreator());
        final Item updatedItem = itemRepository.save(item);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/items/{id}")
    public Map < String, Boolean > deleteItem(@PathVariable(value = "id") Long itemId)
    throws ResourceNotFoundException {
        Item item = itemRepository.findOne(itemId);

        itemRepository.delete(item);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
