package com.sag.routes.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;




import com.sag.routes.model.GuardianUser;
import com.sag.routes.model.LibrarianUser;
import com.sag.routes.model.Organization;
/*import com.sag.routes.model.BusDetails;
import com.sag.routes.model.Route;
import com.sag.routes.model.RouteDTO;*/
import com.sag.routes.model.Student;
import com.sag.routes.service.ServiceI;

//RestController which contains all REST endpoints

@RestController
@RequestMapping("/organization")     //sample endpoint--- localhost:8080/rest/bus
public class OrganizationController {

	final static Logger logger = Logger.getLogger(RouteController.class);

	@Autowired
	private ServiceI serviceI;
	

	// Route Controller
	
	/*
	 * @GetMapping--specifies GET method
	 * @PostMapping--specifies POST method
	 * @PutMapping--specifies PUT method
	 * @DeleteMapping--specifies DELETE method
	 */
	
	@GetMapping("/organization/{id}")   //sample endpoint---- localhost:8080/rest/bus/route/{id}
	public ResponseEntity<Organization> getOrganizationById(@PathVariable("id") Integer id) {
		Organization organization = serviceI.getOrganizationById(id);
		return new ResponseEntity<Organization>(organization , HttpStatus.OK);
	}
	@CrossOrigin
	@GetMapping("/organizations")
	public ResponseEntity<List<Organization>> getAllOrganization() {
		List<Organization> list = serviceI.getAllOrganization();
		return new ResponseEntity<List<Organization>>(list, HttpStatus.OK);
	}

	@PostMapping("/organization")
	public ResponseEntity<Void> addOrganization(@RequestBody Organization organization, UriComponentsBuilder builder) {
		boolean flag = serviceI.addOrganization(organization);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/librarian/{id}").buildAndExpand(organization.getoId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("/organization")
	public ResponseEntity<Organization> updateOrganization(@RequestBody Organization organization) {
		serviceI.updateOrganization(organization);
		return new ResponseEntity<Organization>(organization, HttpStatus.OK);
	}

	@DeleteMapping("/organization/{id}")
	public ResponseEntity<Void> deleteOrganization(@PathVariable("id") Integer id) {
		serviceI.deleteOrganization(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	
	/*@CrossOrigin
	@GetMapping("/routenumbers") // eg:/routenumber?source=velachery&destination=madipakkam
	public List<RouteDTO> getBusRoute(@RequestParam(value = "source", required = true) String source,
			@RequestParam(value = "destination", required = false) String destination) {
		List<RouteDTO> route = serviceI.getBusRoute(source, destination);
		logger.info(source + destination);
		return route;

	}*/

	/*// Bus Controller

	@GetMapping("/busdetails/{id}")   //sample endpoint---- localhost:8080/rest/bus/busdetails/{id}
	public ResponseEntity<BusDetails> getBusDetailsById(@PathVariable("id") Integer id) {
		BusDetails busDetails = serviceI.getBusDetailsById(id);
		return new ResponseEntity<BusDetails>(busDetails, HttpStatus.OK);
	}
	@CrossOrigin
	@GetMapping("/busdetails")
	public ResponseEntity<List<BusDetails>> getAllBusDetails() {
		List<BusDetails> list = serviceI.getAllBusDetails();
		return new ResponseEntity<List<BusDetails>>(list, HttpStatus.OK);
	}

	@PostMapping("/createbusdetails")
	public ResponseEntity<Void> addBusDetails(@RequestBody BusDetails busDetails, UriComponentsBuilder builder) {
		boolean flag = serviceI.addBusDetails(busDetails);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/busdetails/{id}").buildAndExpand(busDetails.getBusDetails_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<BusDetails> updateBusDetails(@RequestBody BusDetails busDetails) {
		serviceI.updateBusDetails(busDetails);
		return new ResponseEntity<BusDetails>(busDetails, HttpStatus.OK);
	}

	@DeleteMapping("/deletebusdetails/{id}")
	public ResponseEntity<Void> deleteBusDetails(@PathVariable("id") Integer id) {
		serviceI.deleteBusDetails(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	*/
	
	}
