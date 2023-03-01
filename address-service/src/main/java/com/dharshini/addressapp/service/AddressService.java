package com.dharshini.addressapp.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dharshini.addressapp.entity.Address;
import com.dharshini.addressapp.repository.AddressRepository;
import com.dharshini.addressapp.response.AddressResponse;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public AddressResponse getAddressByEmployeeId(int employeeId) {
		Address address = addressRepository.findAddressByEmployeeId(employeeId);
		AddressResponse addressResponse = modelMapper.map(address,AddressResponse.class);
		return addressResponse;
	}
	
	public List<AddressResponse> getAllAddresses(){
		List<Address> addressList = addressRepository.findAll();
		List<AddressResponse> addressResponseList = Arrays.asList(modelMapper.map(addressList,AddressResponse[].class));
		return addressResponseList;
	}
	
	public ResponseEntity<AddressResponse> createAddress(AddressResponse addressResponse) throws Exception {
		Address address = modelMapper.map(addressResponse,Address.class);
		addressRepository.save(address);
		AddressResponse savedAddressResponse = modelMapper.map(address,AddressResponse.class);
		if(address != null) {
			return new ResponseEntity<>(savedAddressResponse,HttpStatus.CREATED);
		}else {
			throw new Exception();
		}
	}
}
