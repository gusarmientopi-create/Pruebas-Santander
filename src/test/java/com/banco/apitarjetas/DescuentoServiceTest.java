package com.banco.apitarjetas;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import com.banco.apitarjetas.repository.ClienteRepository;
import com.banco.apitarjetas.service.DescuentoService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
@ExtendWith(MockitoExtension.class)
public class DescuentoServiceTest {
@Mock
private ClienteRepository repository;
@InjectMocks
private DescuentoService service;
@Test
void compraRegularSinDescuento() {
	when(repository.esvip("A1")).thenReturn(false);
	double resultado= service.calcularTotal(100000, "A1");
	assertEquals(100000,resultado);
}
@Test
void compraMayor500Regular() {
	when(repository.esvip("A2")).thenReturn(false);
	double resultado= service.calcularTotal(1000000, "A2");
	assertEquals(900000,resultado);
}
@Test
void compraMayor500Vip() {
	when(repository.esvip("A3")).thenReturn(true);
	double resultado= service.calcularTotal(1000000, "A3");
	assertEquals(850000,resultado);
}
@Test
void descuentoNoDebeSuperar15Porciento() {
	when(repository.esvip("A4")).thenReturn(true);
	double resultado= service.calcularTotal(600000, "A4");
	assertEquals(510000,resultado);
}
@Test
void debeLanzarExcepcionSiValorInvalido() {
	assertThrows(IllegalArgumentException.class,()-> {service.calcularTotal(0,"A5");} );
	assertThrows(IllegalArgumentException.class,()-> {service.calcularTotal(-500,"A5");} );
}
}
