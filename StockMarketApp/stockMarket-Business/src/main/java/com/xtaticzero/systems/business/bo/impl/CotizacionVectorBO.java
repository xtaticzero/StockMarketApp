/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.bo.impl;

import com.xtaticzero.systems.base.dto.CotizacionDiariaDTO;
import com.xtaticzero.systems.base.dto.CotizacionHistoricoDTO;
import com.xtaticzero.systems.base.dto.IPCDto;
import com.xtaticzero.systems.base.dto.UsuarioDTO;
import com.xtaticzero.systems.business.bo.BO;
import java.util.List;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class CotizacionVectorBO extends BO<CotizacionVectorBO> {

    private static final long serialVersionUID = -7941723588237593411L;

    private List<CotizacionDiariaDTO> lstCotizacionesDiarias;
    private List<CotizacionHistoricoDTO> lstHistoricoCotizacion;
    private CotizacionDiariaDTO cotizacionSeleccionada;
    private CotizacionDiariaDTO cotizacionAnual;
    private List<IPCDto> lstIpc;
    private List<IPCDto> lstIpcCargaExcel;
    private IPCDto ipcNew;
    private IPCDto ipcUltimoAnual;

    private CotizacionVectorBO(UsuarioDTO usuario) {
        super(usuario);
    }

    public static CotizacionVectorBO getBOValido(UsuarioDTO usuario) {
        if (usuario != null && usuario.getRol_id() != null && usuario.getDisplay_name() != null) {
            return new CotizacionVectorBO(usuario);
        }
        return null;
    }

    public List<CotizacionDiariaDTO> getLstCotizacionesDiarias() {
        return lstCotizacionesDiarias;
    }

    public void setLstCotizacionesDiarias(List<CotizacionDiariaDTO> lstCotizacionesDiarias) {
        this.lstCotizacionesDiarias = lstCotizacionesDiarias;
    }

    public CotizacionDiariaDTO getCotizacionSeleccionada() {
        return cotizacionSeleccionada;
    }

    public void setCotizacionSeleccionada(CotizacionDiariaDTO cotizacionSeleccionada) {
        this.cotizacionSeleccionada = cotizacionSeleccionada;
    }

    public List<CotizacionHistoricoDTO> getLstHistoricoCotizacion() {
        return lstHistoricoCotizacion;
    }

    public void setLstHistoricoCotizacion(List<CotizacionHistoricoDTO> lstHistoricoCotizacion) {
        this.lstHistoricoCotizacion = lstHistoricoCotizacion;
    }

    public List<IPCDto> getLstIpc() {
        return lstIpc;
    }

    public void setLstIpc(List<IPCDto> lstIpc) {
        this.lstIpc = lstIpc;
    }

    public List<IPCDto> getLstIpcCargaExcel() {
        return lstIpcCargaExcel;
    }

    public void setLstIpcCargaExcel(List<IPCDto> lstIpcCargaExcel) {
        this.lstIpcCargaExcel = lstIpcCargaExcel;
    }

    public CotizacionDiariaDTO getCotizacionAnual() {
        return cotizacionAnual;
    }

    public void setCotizacionAnual(CotizacionDiariaDTO cotizacionAnual) {
        this.cotizacionAnual = cotizacionAnual;
    }

    public IPCDto getIpcNew() {
        return ipcNew;
    }

    public void setIpcNew(IPCDto ipcNew) {
        this.ipcNew = ipcNew;
    }

    public IPCDto getIpcUltimoAnual() {
        return ipcUltimoAnual;
    }

    public void setIpcUltimoAnual(IPCDto ipcUltimoAnual) {
        this.ipcUltimoAnual = ipcUltimoAnual;
    }

}
