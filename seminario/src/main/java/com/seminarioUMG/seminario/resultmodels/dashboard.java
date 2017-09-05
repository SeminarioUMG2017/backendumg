package com.seminarioUMG.seminario.resultmodels;

public class dashboard {
	
	
	private Long entradasVendidas;
	private Long TotalAlumnos;
	private Long TotaldeAlumnosIngresados;
	private Long TotaldeDiplomas;
	public Long getEntradasVendidas() {
		return entradasVendidas;
	}
	public void setEntradasVendidas(Long entradasVendidas) {
		this.entradasVendidas = entradasVendidas;
	}
	public Long getTotalAlumnos() {
		return TotalAlumnos;
	}
	public void setTotalAlumnos(Long totalAlumnos) {
		TotalAlumnos = totalAlumnos;
	}
	public Long getTotaldeAlumnosIngresados() {
		return TotaldeAlumnosIngresados;
	}
	public void setTotaldeAlumnosIngresados(Long totaldeAlumnosIngresados) {
		TotaldeAlumnosIngresados = totaldeAlumnosIngresados;
	}
	public Long getTotaldeDiplomas() {
		return TotaldeDiplomas;
	}
	public void setTotaldeDiplomas(Long totaldeDiplomas) {
		TotaldeDiplomas = totaldeDiplomas;
	}
	public dashboard(Long entradasVendidas, Long totalAlumnos, Long totaldeAlumnosIngresados, Long totaldeDiplomas) {
		super();
		this.entradasVendidas = entradasVendidas;
		TotalAlumnos = totalAlumnos;
		TotaldeAlumnosIngresados = totaldeAlumnosIngresados;
		TotaldeDiplomas = totaldeDiplomas;
	}
	public dashboard() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}
