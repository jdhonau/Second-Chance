package com.techelevator.dao;

import com.techelevator.model.Owner;
import java.util.List;

public interface OwnerDao {

    Owner getOwnerById(int ownerId);

    List<Owner> getAllOwners();

    Owner updateOwner(Owner owner);

    Owner deleteOwner(int ownerId);

    Owner createOwner(Owner newOwner);
}
