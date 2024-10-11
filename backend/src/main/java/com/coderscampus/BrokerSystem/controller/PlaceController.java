package com.coderscampus.BrokerSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderscampus.BrokerSystem.domain.Account;
import com.coderscampus.BrokerSystem.domain.Place;
import com.coderscampus.BrokerSystem.dto.PlaceDto;
import com.coderscampus.BrokerSystem.enums.AuthorityEnum;
import com.coderscampus.BrokerSystem.service.PlaceService;
import com.coderscampus.BrokerSystem.util.AuthorityUtil;

@RestController
@RequestMapping("/api/places")
public class PlaceController {
    @Autowired
    private PlaceService placeService;
    @PostMapping
    public ResponseEntity<?> savePlace(@AuthenticationPrincipal Account account
    ,@RequestBody PlaceDto placeDto ){
     if(AuthorityUtil.hasRole(AuthorityEnum.ROLE_ADMIN.name(), account)){
        return ResponseEntity.ok(placeService.savePlace(placeDto));
     }
     else{
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
     }
    }

    @GetMapping
    public ResponseEntity<?> getAllPlaces(){
        return ResponseEntity.ok(placeService.getAllPlaceses());
    }
    @PutMapping
    public ResponseEntity<?> updatePlace(@AuthenticationPrincipal Account account,
    @RequestBody Place place){
        if(AuthorityUtil.hasRole(AuthorityEnum.ROLE_ADMIN.name(), account)){
            return ResponseEntity.ok(placeService.updatePlace(place));
         }
         else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
         }
    }
   @DeleteMapping("/{placeId}") // Update to use path variable
    public ResponseEntity<?> deletePlace(@AuthenticationPrincipal Account account,
                                          @PathVariable Long placeId) { // Use @PathVariable
        if (AuthorityUtil.hasRole(AuthorityEnum.ROLE_ADMIN.name(), account)) {
            placeService.deletPlace(placeId);
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
