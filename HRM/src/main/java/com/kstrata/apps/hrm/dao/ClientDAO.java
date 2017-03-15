package com.kstrata.apps.hrm.dao;

import java.util.List;

import com.kstrata.apps.hrm.model.Client;

public interface ClientDAO {
	
	public void saveClient(final Client client);

	public void deleteClient(final Client client);

	public Client findByClientId(Integer clientId);

	public Client findByClientName(final String clientName);

	public List<Client> findAll();

}
