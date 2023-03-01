package com.heaiai.reggie.dao;

import com.heaiai.reggie.entity.AddressBook;

public interface AddressBookMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AddressBook record);

    int insertSelective(AddressBook record);

    AddressBook selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AddressBook record);

    int updateByPrimaryKey(AddressBook record);
}