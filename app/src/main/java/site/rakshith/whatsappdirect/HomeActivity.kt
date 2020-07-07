package site.rakshith.whatsappdirect

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.rilixtech.widget.countrycodepicker.CountryCodePicker
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        /* hide action bar */
        supportActionBar?.hide()

        val ccp = findViewById<CountryCodePicker>(R.id.ccp) //country code picker
        val phoneNumberEditText = findViewById<EditText>(R.id.phoneNo) //phone number edittext
        val submitBtn = findViewById<Button>(R.id.submitBtnWhatsapp)
        val submitBtnBusiness =findViewById<Button>(R.id.submitBtnWhatsappBusiness)
        val messageEditText = findViewById<EditText>(R.id.message)
        var countryCode = "+91"
        var message = ""

        //ccp.setCountryForNameCode("IN")
        ccp.enableSetCountryByTimeZone(true)


        /*
         * Check if WhatApp and WhatsApp Business Installed, Hide buttons if not Installed
         */

        var unAvailable = 0
        var pm : PackageManager = applicationContext.packageManager
        try {
                pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA)
        } catch (e: PackageManager.NameNotFoundException) {
                submitBtn.visibility = View.GONE
            unAvailable++
        }

        try {
            pm.getPackageInfo("com.whatsapp.w4b", PackageManager.GET_META_DATA)
        } catch (e: PackageManager.NameNotFoundException) {
                submitBtnWhatsappBusiness.visibility = View.GONE
            unAvailable++
        }

        if(unAvailable==2){
            /*  Show alert when back button is pressed  */
            this?.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton("OK",
                        DialogInterface.OnClickListener { dialog, id ->
                            // User clicked OK button
                            finish();
                            System.exit(0);
                        })
                    setMessage(
                        "You don't have WhatsApp installed in this device, " +
                                "Please install WhatsApp or WhatsApp Business " +
                                "to make use of this application relevant on your device"
                    )
                }
                // Set other dialog properties
                builder.setCancelable(false)

                // Create the AlertDialog
                builder.create()
            }?.show()
        }

        ccp.setOnCountryChangeListener() {
            countryCode = ccp.selectedCountryCodeWithPlus
            Toast.makeText(this, "selected country code is $countryCode", Toast.LENGTH_LONG ).show()
        }

        submitBtn.setOnClickListener {

            if( phoneNumberEditText.text != null && phoneNumberEditText.text.length == 10){
                val phoneNumber = phoneNumberEditText.text
                if(messageEditText.text!= null){
                    message = messageEditText.text.toString()
                }
                openWhatsAppContact("$countryCode $phoneNumber",message,0)

            }else{
                Toast.makeText(this, "Invalid Number", Toast.LENGTH_LONG ).show()
                phoneNumberEditText?.error = "Enter valid number"
            }
        }

        submitBtnBusiness.setOnClickListener {
            if( phoneNumberEditText.text != null && phoneNumberEditText.text.length == 10){
                var phoneNumber = phoneNumberEditText.text
                if(messageEditText.text!= null){
                    message = messageEditText.text.toString()
                }
                openWhatsAppContact("$countryCode $phoneNumber",message,1)

            }else{
                Toast.makeText(this, "Invalid Number", Toast.LENGTH_LONG ).show()
                phoneNumberEditText?.error = "Enter valid number"
            }
        }
    }



    /**
     * Function opens intent to WhatsApp or WhatsApp Business based on params provided
     * @param number : phone number with country code in string
     * @param message : message for message
     * @param target : Int specifies what to opens WhatsApp if set to 0, Opens W4b if set to 1
     */

    private fun openWhatsAppContact (number:String, message:String, target:Int ) {

        if(target==0) {
            val uri = Uri.parse("https://wa.me/$number/?text=$message")
            val sendIntent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(sendIntent)

        } else if(target == 1) {
            var uri = Uri.parse("smsto:$number")
            var i = Intent(Intent.ACTION_SENDTO, uri)
            i.putExtra(Intent.EXTRA_TEXT, "some message")
            i.`package` = "com.whatsapp.w4b"
            startActivity(Intent.createChooser(i, "Choose Action"));

            Toast.makeText(this,
                "Currently WhatsApp Business does not support message text directly from application",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onBackPressed() {

        /*  Show alert when back button is pressed  */
        this?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton("Yes",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User clicked OK button
                        finish();
                        System.exit(0);
                    })
                setNegativeButton("No",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                        dialog.dismiss()
                    })
                setMessage("Do you really want to exit the application")
            }
            // Set other dialog properties
            builder.setCancelable(false)

            // Create the AlertDialog
            builder.create()
        }?.show()
//        super.onBackPressed()
    }
}

