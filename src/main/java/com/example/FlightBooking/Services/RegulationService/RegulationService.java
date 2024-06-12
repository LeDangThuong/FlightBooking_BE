package com.example.FlightBooking.Services.RegulationService;

import com.example.FlightBooking.DTOs.Request.AirlineAndAirport.AirlineDTO;
import com.example.FlightBooking.DTOs.Request.AirlineAndAirport.PlaneDTO;
import com.example.FlightBooking.DTOs.Request.RegulationDTO;
import com.example.FlightBooking.Models.Airlines;
import com.example.FlightBooking.Models.Planes;
import com.example.FlightBooking.Models.Regulation;
import com.example.FlightBooking.Repositories.AirlinesRepository;
import com.example.FlightBooking.Repositories.PlaneRepository;
import com.example.FlightBooking.Repositories.RegulationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegulationService {

    @Autowired
    private RegulationRepository regulationRepository;
    @Autowired
    private AirlinesRepository airlineRepository;
    @Transactional
    public List<RegulationDTO> getAllPricingByAirline(Long airlineId) {
        List<Regulation> regulations = regulationRepository.findByAirlineId(airlineId);
        return regulations.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    @Transactional
    public RegulationDTO getPricingById(Long id) {
        Regulation regulation = regulationRepository.findById(id).orElseThrow(() -> new RuntimeException("Regulation not found"));
        return convertToDTO(regulation);
    }
    @Transactional
    public RegulationDTO savePricing(Regulation pricing) {
        Regulation savedRegulation = regulationRepository.save(pricing);
        return convertToDTO(savedRegulation);
    }
    @Transactional
    public void deletePricing(Long id) {
        regulationRepository.deleteById(id);
    }
    @Transactional
    public RegulationDTO updatePricing(Long id, Double firstClassPrice, Double businessPrice, Double economyPrice) {
        Regulation regulation = regulationRepository.findById(id).orElseThrow(() -> new RuntimeException("Regulation not found"));
        if (firstClassPrice != null) {
            regulation.setFirstClassPrice(firstClassPrice);
        }
        if (businessPrice != null) {
            regulation.setBusinessPrice(businessPrice);
        }
        if (economyPrice != null) {
            regulation.setEconomyPrice(economyPrice);
        }
        Regulation updatedRegulation = regulationRepository.save(regulation);
        return convertToDTO(updatedRegulation);
    }
    @Transactional
    public RegulationDTO getRegulationByAirlineId(Long airlineId) {
        Airlines airline = airlineRepository.findById(airlineId).orElseThrow(() -> new RuntimeException("Airline not found"));
        Regulation regulation = regulationRepository.findByAirline(airline);
        return convertToDTO(regulation);
    }
    @Transactional
    public List<AirlineDTO> getAllAirlinesWithRegulations() {
        List<Airlines> airlines = airlineRepository.findAll();
        return airlines.stream().map(this::convertToAirlineDTO).collect(Collectors.toList());
    }
    @Transactional
    private RegulationDTO convertToDTO(Regulation regulation) {
        RegulationDTO dto = new RegulationDTO();
        dto.setId(regulation.getId());
        dto.setFirstClassPrice(regulation.getFirstClassPrice());
        dto.setBusinessPrice(regulation.getBusinessPrice());
        dto.setEconomyPrice(regulation.getEconomyPrice());
        dto.setAirline(convertToDTO(regulation.getAirline()));
        return dto;
    }
    @Transactional
    private AirlineDTO convertToDTO(Airlines airline) {
        AirlineDTO dto = new AirlineDTO();
        dto.setId(airline.getId());
        dto.setAirlineName(airline.getAirlineName());
        dto.setLogoUrl(airline.getLogoUrl());
        dto.setPromoForAirline(airline.getPromoForAirline());
        return dto;
    }

    @Transactional
    private AirlineDTO convertToAirlineDTO(Airlines airline) {
        AirlineDTO dto = new AirlineDTO();
        dto.setId(airline.getId());
        dto.setAirlineName(airline.getAirlineName());
        dto.setLogoUrl(airline.getLogoUrl());
        dto.setPromoForAirline(airline.getPromoForAirline());
        // Assuming you have a method to convert planes
        dto.setPlanes(airline.getPlanes().stream().map(this::convertToPlaneDTO).collect(Collectors.toList()));
        return dto;
    }
    @Transactional
    private PlaneDTO convertToPlaneDTO(Planes plane) {
        PlaneDTO dto = new PlaneDTO();
        dto.setId(plane.getId());
        dto.setFlightNumber(plane.getFlightNumber());
        // other fields
        return dto;
    }

}
