package com.barclays.ibm.msscbrewery.service;

import com.barclays.ibm.msscbrewery.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId);
}
