package com.banco.apitarjetas.service;

import org.springframework.stereotype.Service;

import com.banco.apitarjetas.repository.ClienteRepository;

@Service
public class DescuentoService {
 private final ClienteRepository repository ;
 public DescuentoService(ClienteRepository repository){
	 this.repository= repository;
 }
 public double calcularTotal (double valorCompra,String clienteId) {
	 if(valorCompra <= 0) {
		 throw new IllegalArgumentException("El valor es menor");
	 }
	 double porcentajeDescuento=0.0;
	 if(valorCompra>500000) {
		 porcentajeDescuento =(porcentajeDescuento + 0.10);
	 }
	 if(repository.esvip(clienteId)) {
		 porcentajeDescuento =(porcentajeDescuento + 0.5);
	 }
	 porcentajeDescuento =Math.min(porcentajeDescuento, 0.15);
	 double descuento = valorCompra * porcentajeDescuento;
	 return valorCompra - descuento;
 }
}
