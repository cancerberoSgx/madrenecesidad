package org.sgx.madrenecesidad.server.core;

import org.sgx.madrenecesidad.client.model.User;

public interface UserCoreService {
String addUser(User s, User u);
User getById(User s, String id); 
void removeUser(User s, User u);

}
