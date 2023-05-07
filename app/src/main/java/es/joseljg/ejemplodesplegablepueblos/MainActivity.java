package es.joseljg.ejemplodesplegablepueblos;

import androidx.appcompat.app.AppCompatActivity;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import es.joseljg.ejemplodesplegablepueblos.clases.Comunidad;
import es.joseljg.ejemplodesplegablepueblos.clases.Provincia;
import es.joseljg.ejemplodesplegablepueblos.clases.Pueblo;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ArrayList<Comunidad> comunidades;
    private ArrayList<Provincia> provincias;
    private ArrayList<Pueblo> pueblos;

    private Spinner sp_comunidad;
    private Spinner sp_provincia;
    private Spinner sp_pueblo;

    private Comunidad comunidadSeleccionada;
    private Provincia provinciaSeleccionada;
    private Pueblo puebloSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //----------------------------------------------------------------
        comunidades = leerComunidadesDesdeCSV();
        provincias = leerProvinciasDesdeCSV();
        pueblos = leerPueblosDesdeCSV();
        //----------------------------------------------------------------
        sp_comunidad = (Spinner) findViewById(R.id.sp_comunidad);
        sp_provincia = (Spinner) findViewById(R.id.sp_provincia);
        sp_pueblo = (Spinner) findViewById(R.id.sp_pueblo);
        //----------------------------------------------------------------
        sp_comunidad.setOnItemSelectedListener(this);
        ArrayAdapter<Comunidad> adaptador_comunidad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, comunidades);
        adaptador_comunidad.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sp_comunidad.setAdapter(adaptador_comunidad);
        //---------------------------------------------------------------
        sp_provincia.setOnItemSelectedListener(this);
        sp_pueblo.setOnItemSelectedListener(this);

    }

//--------------------------------------------------------------------------------------------------
    private ArrayList<Pueblo> leerPueblosDesdeCSV() {
        InputStream is = getResources().openRawResource(R.raw.pueblos_por_provincias);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8")));
        String line = "";
        ArrayList<Pueblo> lospueblos = new ArrayList<Pueblo>();
        try {
            while ((line = reader.readLine()) != null) {
                  String[] datos = line.split(",");
                  String codigo_provincia = datos[0];
                  String codigo_pueblo = datos[1];
                  String nombre_pueblo = datos[2];
                  Pueblo p = new Pueblo(codigo_provincia, codigo_pueblo, nombre_pueblo);
                  lospueblos.add(p);
            }
        } catch (IOException e1) {
            System.out.println("no pude abrir el fichero de los pueblos");
            Log.i("csv", "no pude abrir el fichero de los pueblos");
        }
        return lospueblos;
    }
//---------------------------------------------------------------------------------------------------
    private ArrayList<Provincia> leerProvinciasDesdeCSV() {
        InputStream is = getResources().openRawResource(R.raw.provincias_por_comunidad);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8")));
        String line = "";

        ArrayList<Provincia> lasprovincias = new ArrayList<Provincia>();
        try {
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");
                String nombre_comunidad = datos[0];
                String codigo_comunidad = datos[1];
                String nombre1_provincia = datos[2];
                String nombre2_provincia = datos[3];
                String codigo_provincia = datos[4];
                Provincia p = new Provincia(nombre_comunidad,codigo_comunidad,nombre1_provincia,nombre2_provincia,codigo_provincia);
                lasprovincias.add(p);
            }
        } catch (IOException e1) {
            System.out.println("no pude abrir el fichero de provincias");
            Log.i("csv", "no pude abrir el fichero de provincias");
        }
        return lasprovincias;
    }

    //------------------------------------------------------------------------------------------------------------------------------
    private ArrayList<Comunidad> leerComunidadesDesdeCSV() {
        InputStream is = getResources().openRawResource(R.raw.comunidades);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8")));
        String line = "";

        ArrayList<Comunidad> lascomunidades = new ArrayList<Comunidad>();
        try {
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");
                String nombre_comunidad = datos[0];
                String codigo_comunidad = datos[1];
                Comunidad c = new Comunidad(nombre_comunidad,codigo_comunidad);
                lascomunidades.add(c);
            }
        } catch (IOException e1) {
            System.out.println("no pude abrir el fichero de provincias");
            Log.i("csv", "no pude abrir el fichero de provincias");
        }
        return lascomunidades;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch(adapterView.getId()) {
            case R.id.sp_comunidad:
                if(comunidadSeleccionada == null)
                {
                    comunidadSeleccionada = new Comunidad("Castilla - La Mancha","08");
                }
                comunidadSeleccionada  = (Comunidad)sp_comunidad.getSelectedItem();
                String codigo_comunidad = comunidadSeleccionada.getCodigo_comunidad();
                // cargo las provincias
                ArrayList<Provincia> lasProvinciasComunidad = ObtenerProvinciasDeComunidad(comunidadSeleccionada);
                ArrayAdapter<Provincia> adaptador_provincia = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lasProvinciasComunidad);
                adaptador_provincia.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                sp_provincia.setAdapter(adaptador_provincia);
                break;

                case R.id.sp_provincia:
                    if(provinciaSeleccionada == null)
                    {
                        provinciaSeleccionada = new Provincia("Castilla - La Mancha","08","Toledo","Toledo","45");
                    }
                provinciaSeleccionada  = (Provincia) sp_provincia.getSelectedItem();
                String codigo_provincia = provinciaSeleccionada.getCodigo_provincia();
                // cargo las provincias
                    ArrayList<Pueblo> losPueblosProvincias = ObtenerPueblosDeProvincia(provinciaSeleccionada);
                ArrayAdapter<Pueblo> adaptador_pueblo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, losPueblosProvincias);
                adaptador_pueblo.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                sp_pueblo.setAdapter(adaptador_pueblo);
                break;

            case R.id.sp_pueblo:
                if(puebloSeleccionado == null)
                {
                    puebloSeleccionado = new Pueblo("45","161","Seseña");
                }
                puebloSeleccionado  = (Pueblo) sp_pueblo.getSelectedItem();
                break;
        }

    }

    private ArrayList<Pueblo> ObtenerPueblosDeProvincia(Provincia provinciaSeleccionada) {
        ArrayList<Pueblo> pueblosProvincia = new ArrayList<Pueblo>();
        String codigoProvincia = provinciaSeleccionada.getCodigo_provincia();
        for (Pueblo p: pueblos )
        {
            if(p.getCodigo_provincia().equalsIgnoreCase(codigoProvincia))
            {
                pueblosProvincia.add(p);
            }
        }
        return pueblosProvincia;
    }


    private ArrayList<Provincia> ObtenerProvinciasDeComunidad(Comunidad comunidadSeleccionada) {
              ArrayList<Provincia> provinciasComunidad = new ArrayList<Provincia>();
              String codigoComunidad = comunidadSeleccionada.getCodigo_comunidad();
        for (Provincia p: provincias )
        {
            if(p.getCodigo_comunidad().equalsIgnoreCase(codigoComunidad))
            {
                provinciasComunidad.add(p);
            }
        }
        return provinciasComunidad;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
         // si no selecciono nada
        comunidadSeleccionada = new Comunidad("Castilla - La Mancha","08");
        provinciaSeleccionada = new Provincia("Castilla - La Mancha","08","Toledo","Toledo","45");
        puebloSeleccionado = new Pueblo("45","161","Seseña");
    }
}