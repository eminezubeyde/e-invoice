package com.example.einvoice.service;

import com.example.einvoice.core.exception.GeneralException;

import java.util.List;

public interface UserService {
   List<String> getUserRoles(String username) throws GeneralException;
}
