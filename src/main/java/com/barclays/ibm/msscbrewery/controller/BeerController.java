package com.barclays.ibm.msscbrewery.controller;

import com.barclays.ibm.msscbrewery.model.BeerDto;
import com.barclays.ibm.msscbrewery.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BeerController {

    private final BeerService beerService;

    private static final String BEER_PATH = "/api/v1/beer";
    private static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

    @GetMapping(BEER_PATH_ID)
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping(BEER_PATH)
    public ResponseEntity<BeerDto> createBeer(@RequestBody BeerDto beerDto) {
        BeerDto savedDto = beerService.createBeer(beerDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("location", BEER_PATH + "/" + savedDto.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(BEER_PATH_ID)
    public ResponseEntity<BeerDto> updateBeer(@PathVariable("beerId") UUID beerId,
                                              @RequestBody BeerDto beerDto) {
        beerService.updateBeer(beerId, beerDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEER_PATH_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId) {
        beerService.deleteById(beerId);
    }
}