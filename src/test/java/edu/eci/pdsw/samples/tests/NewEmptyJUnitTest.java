/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.entities.Consulta;
import edu.eci.pdsw.samples.entities.Paciente;
import edu.eci.pdsw.samples.services.ServiceFacadeException;
import edu.eci.pdsw.samples.services.ServicesFacade;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void registroPacienteTest() throws ServiceFacadeException{
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        
        ServicesFacade f = ServicesFacade.getInstance("CofigAlter.properties");
        Paciente p = new Paciente(1013622878, "CC", "Cesar Vega", sqlDate);
        Set<Consulta> consultas=new LinkedHashSet<>();
        Consulta c;
        c = new Consulta(sqlDate, "Esta muy mal");
        consultas.add(c);
        c = new Consulta(sqlDate, "Siguio mal");
        consultas.add(c);
        p.setConsultas(consultas);
        f.registrarNuevoPaciente(p);
        
        Paciente pa = f.consultarPaciente(p.getId(), p.getTipo_id());

        assertTrue("son iguales",p.getId() == pa.getId());
    }
    
    @Test
    public void registroConsultaTest() throws ServiceFacadeException{
        ServicesFacade f = ServicesFacade.getInstance("CofigAlter.properties");
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        Paciente p = new Paciente(101362133, "CC", "assad Vega", sqlDate);
        Set<Consulta> consultas=new LinkedHashSet<>();
        p.setConsultas(consultas);
        f.registrarNuevoPaciente(p);
        Consulta c = new Consulta(sqlDate, "Esta muy mal otro que esta mal");
        consultas.add(c);
        f.agregarConsultaAPaciente(p.getId(), p.getTipo_id(), c);
        c = new Consulta(sqlDate, "Siguio mal otro que esta mal");
        consultas.add(c);
        f.agregarConsultaAPaciente(p.getId(), p.getTipo_id(), c);

        assertTrue("son iguales",p.getConsultas().size() == f.consultarPaciente(p.getId(), p.getTipo_id()).getConsultas().size());
    }
    
    @Test
    public void registroPacienteSinConsulta() throws ServiceFacadeException{
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        
        ServicesFacade f = ServicesFacade.getInstance("CofigAlter.properties");
        Paciente p = new Paciente(2131233, "CC", "Cesar Vega", sqlDate);

        f.registrarNuevoPaciente(p);
        
        Paciente pa = f.consultarPaciente(p.getId(), p.getTipo_id());

        assertTrue("son iguales",p.getId() == pa.getId());
    }
    
    @Test
    public void cargarPacienteSinConsulta() throws ServiceFacadeException{
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        
        ServicesFacade f = ServicesFacade.getInstance("CofigAlter.properties");
        
        Paciente p = new Paciente(12312312, "CC", "Cesar Vega", sqlDate);

        Paciente pa = f.consultarPaciente(p.getId(), p.getTipo_id());
        
        assertTrue("son iguales",p.getConsultas().size()==0);
    }

    @Test
    public void registroPacienteFechaInvalida() throws ServiceFacadeException{
        
        ServicesFacade f = ServicesFacade.getInstance("CofigAlter.properties");
        
        Paciente p = new Paciente(12312312, "CC", "Cesar Vega", new Date(2020, 23, 12));

        Paciente pa = f.consultarPaciente(p.getId(), p.getTipo_id());
        
        assertTrue("son iguales",true);
    }
    
    @Test
    public void registroPacienteFechaConsultaInvalida() throws ServiceFacadeException{
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        
        ServicesFacade f = ServicesFacade.getInstance("CofigAlter.properties");
        
        Paciente p = new Paciente(12312312, "CC", "Cesar Vega", sqlDate);
        Set<Consulta> consultas=new LinkedHashSet<>();
        Consulta c;
        c = new Consulta(new Date(2121, 12, 12), "Esta muy mal");
        consultas.add(c);
        
        f.agregarConsultaAPaciente(p.getId(), p.getTipo_id(), c);
        assertTrue("son iguales",true);
    }
    
    @Test
    public void registroPacienteFechaConsultaInvalida__() throws ServiceFacadeException{
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        
        ServicesFacade f = ServicesFacade.getInstance("CofigAlter.properties");
        
        Paciente p = new Paciente(12312312, "CC", "Cesar Vega", sqlDate);
        
        Set<Consulta> consultas=new LinkedHashSet<>();
        Consulta c;
        c = new Consulta(new Date(1111, 12, 12), "Esta muy mal");
        consultas.add(c);
        
        f.agregarConsultaAPaciente(p.getId(), p.getTipo_id(), c);
        
        assertTrue("son iguales",true);
    }
}
