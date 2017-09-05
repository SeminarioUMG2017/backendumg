package com.seminarioUMG.seminario.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seminarioUMG.seminario.model.Alumno;
import com.seminarioUMG.seminario.resultmodels.dashboard;
import com.seminarioUMG.seminario.services.AlumnoService;

@Primary 
@RestController
public class DashboardController {
	
	@Autowired
	@Qualifier("AlumnoService")
	 
	AlumnoService dash;
	
    @GetMapping(value = "/dashboard")
    public dashboard getInfo()  {
    	
    	dashboard dashboards = new dashboard();
    	dashboards.setEntradasVendidas(dash.totalEntradas());
    	dashboards.setTotalAlumnos(dash.totalAlumnos());
    	dashboards.setTotaldeAlumnosIngresados(dash.totalIngresos());
    	dashboards.setTotaldeDiplomas(dash.totalDiplomas());
    	
 
		return dashboards;
    	
    
    	
    }

	

}
