package com.example.navarro.androidpractice.web.managers;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by ${Navarro} on 9/14/17.
 */

public class SoapServiceManager {
    //region Variables
    public static String URL = "http://www.webservicex.net/ConvertTemperature.asmx";
    public static String SOAP_ACTION = "http://www.webserviceX.NET/ConvertTemp";
    public static String NAMESPACE = "http://www.webserviceX.NET/";
    public static String METHOD = "ConvertTemp";
    //endregion

    //region Properties
    public static String PROPERTY_TEMPERATURE = "Temperature";
    public static String PROPERTY_FROM_UNIT = "FromUnit";
    public static String PROPERTY_TO_UNIT = "ToUnit";
    //endregion

    //region Units Options
    public static String UNIT_CELSIUS = "degreeCelsius";
    public static String UNIT_FAHRENHEIT = "degreeFahrenheit";
    public static String UNIT_RANKINE = "degreeRankine";
    public static String UNIT_REAUMUR = "degreeReaumur";
    public static String UNIT_KELVIN = "kelvin";
    //endregion

    //region GetInstance
    public static SoapServiceManager getInstance(){
        return new SoapServiceManager();
    }
    //endregion

    //region Constructor
    public SoapServiceManager(){

    }
    //endregion

    //region Operations
    public String convertGrades(String grades, String from, String to){
        String result = null;
        SoapObject request = new SoapObject(NAMESPACE, METHOD);
        request.addProperty(PROPERTY_TEMPERATURE, grades);
        request.addProperty(PROPERTY_FROM_UNIT, from);
        request.addProperty(PROPERTY_TO_UNIT, to);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE ht = new HttpTransportSE(URL);
        ht.debug = true;
        SoapPrimitive response = null;
        try {
            ht.call(SOAP_ACTION, envelope);
            response = (SoapPrimitive) envelope.getResponse();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        if(response != null){
            result = response.toString();
        }

        return result;
    }
    //endregion
}
