package fr.isen.pecorella.androiderestaurant.modele

import com.google.gson.annotations.SerializedName
import fr.isen.pecorella.androiderestaurant.Data


data class DataResult(

  @SerializedName("data" ) var data : ArrayList<Data> = arrayListOf()

):java.io.Serializable