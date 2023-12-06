package com.example.apbd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.btnGuardar)
        btn.setOnClickListener(){
            var nombre = findViewById<TextView>(R.id.txtNombre).text.toString()
            var dni = findViewById<TextView>(R.id.txtDNI).text.toString()
            var cod = findViewById<TextView>(R.id.txtCod).text.toString()
            insertarDatos(cod,nombre,dni)
        }
    }
    fun insertarDatos(cod:String,nombre:String,dni:String){
        val queue = Volley.newRequestQueue(applicationContext)
        val URL = "http://10.0.2.2:8080/serviciosWeb/guardarDatosTrab.php"
        val StringRequest = object : StringRequest(
            Request.Method.POST,URL,Response.Listener<String> {response ->
            Toast.makeText(this,"Datos Insertados Correctamente", Toast.LENGTH_SHORT).show()},
            Response.ErrorListener{error -> Toast.makeText(this,"Error ${error.message}",Toast.LENGTH_SHORT).show()}
        ){
            override fun getParams(): MutableMap<String,String>
            {
                val params = HashMap<String,String>()
                params["datosTrab1"] = cod
                params["datosTrab2"] = nombre
                params["datosTrab3"] = dni
                return params
            }
        }
        queue.add(StringRequest)
    }

}