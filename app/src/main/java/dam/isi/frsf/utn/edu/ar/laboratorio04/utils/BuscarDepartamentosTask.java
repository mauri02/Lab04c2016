package dam.isi.frsf.utn.edu.ar.laboratorio04.utils;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Ciudad;
import dam.isi.frsf.utn.edu.ar.laboratorio04.modelo.Departamento;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.BusquedaFinalizadaListener;
import dam.isi.frsf.utn.edu.ar.laboratorio04.utils.FormBusqueda;

/**
 * Created by martdominguez on 22/09/2016.
 */
public class BuscarDepartamentosTask extends AsyncTask<FormBusqueda,Integer,List<Departamento>> {

    private BusquedaFinalizadaListener<Departamento> listener;

    public BuscarDepartamentosTask(BusquedaFinalizadaListener<Departamento> dListener){
        this.listener = dListener;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<Departamento> departamentos) {
        listener.busquedaFinalizada(departamentos);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        listener.busquedaActualizada("departamento "+values[0]);


    }

    @Override
    protected List<Departamento> doInBackground(FormBusqueda... busqueda) {
        List<Departamento> todos = Departamento.getAlojamientosDisponibles();
        List<Departamento> resultado = new ArrayList<Departamento>();
        int  cantPersonas, contador = 0;
        Boolean permFumar;
        Double precioMaximo, precioMinimo;
        Ciudad ciudadBuscada = busqueda[0].getCiudad();
        precioMaximo = busqueda[0].getPrecioMaximo();
        precioMinimo = busqueda[0].getPrecioMinimo();
        cantPersonas = busqueda[0].getHuespedes();
        permFumar = busqueda[0].getPermiteFumar();


        for (Departamento depto:todos) {
            if(permFumar)
                if(ciudadBuscada.equals(depto.getCiudad()))
                    if(depto.getCapacidadMaxima().equals(cantPersonas))
                        if(depto.getPrecio()>=precioMinimo && depto.getPrecio()<=precioMaximo)
                            resultado.add(depto);
            
        }

        
        
        
        
        // TODO implementar: buscar todos los departamentos del sistema e ir chequeando las condiciones 1 a 1.
        // si cumplen las condiciones agregarlo a los resultados.
        return resultado;
    }
}
