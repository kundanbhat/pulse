package com.ie.Pulse.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ie.Pulse.entity.Party;
import com.ie.Pulse.repository.PartyRepo;


@Service
public class PartyService {

	@Autowired
	private PartyRepo partyRepo;

	
	public List<Party> getParty(String name)
	{
		return partyRepo.findByName(name);
	}
	public void Save(Party party)
	{
		partyRepo.save(party);
	}
	public Party getParty(long id)
	{
		return partyRepo.findById(id).get();
	}
	
	
	
}
