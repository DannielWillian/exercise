package com.dannieln.restapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.dannieln.restapi.model.Item;

/**
 * Repository class for database operations of the Item class.
 */
public interface ItemRepository extends CrudRepository<Item, Integer> {
}
