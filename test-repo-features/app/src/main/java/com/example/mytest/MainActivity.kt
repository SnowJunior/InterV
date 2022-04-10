package com.example.mytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mytest.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var userGender by Delegates.notNull<Boolean>()
    var maritalStatus by Delegates.notNull<Boolean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        nameFocusListener()

        binding.viewList.setOnClickListener {
            startActivity(Intent(applicationContext, student_display::class.java))
        }

        binding.submitButton.setOnClickListener{
            submitForm()
        }

        val arrayList_country = arrayListOf<String>("Select a country","Kenya")
        val arrayAdapter_country = ArrayAdapter(this,R.layout.spinnerfill,arrayList_country)
        binding.countrySpinner.adapter=arrayAdapter_country

        binding.radioGroupGender.setOnCheckedChangeListener { radGroup, _ ->
            when(radGroup.checkedRadioButtonId) {
                R.id.btnMale -> userGender = true
                R.id.btnFemale -> userGender = false
            }
        }

        binding.maritalRadioGroup.setOnCheckedChangeListener {radGroup, _ ->
            when(radGroup.checkedRadioButtonId) {
                R.id.single -> maritalStatus = false
                R.id.married -> maritalStatus = true
            }
        }

    }

    private fun submitForm() {
        val validName : EditText = binding.nameEditText
        val validAge : EditText = binding.ageEditText
        val validIqTest : EditText = binding.iqTestEditText

        val _iqCheck = Integer.parseInt(validIqTest.text.toString())

        if (_iqCheck < 100) {
            validIqTest.error = "Invalid input"
            return
        }

        if (validName.text != null && validAge != null && validIqTest != null) {
            val _testData = TestDataClass(
                name = binding.nameEditText.text.toString(),
                age = binding.ageEditText.text.toString(),
                iq = binding.iqTestEditText.text.toString(),
                gender = userGender,
                maritalStatus =  maritalStatus
            )

            testDataList.add(_testData)

            Toast.makeText(applicationContext, testDataList.size.toString(), Toast.LENGTH_LONG).show()
            resetForm()


        }
        else
            invalidForm()
    }

    private fun invalidForm() {
        var message = ""

        if (binding.nameEditText.text != null )
            message += "\n\nName" + binding.nameContainer.helperText
        if (binding.ageEditText.text != null )
            message += "\n\nAge" + binding.ageContainer.helperText
        if (binding.iqTestEditText.text != null )
            message += "\n\nIQ" + binding.iqContainer.helperText

        AlertDialog.Builder(this)
            .setTitle("Invalid Form")
            .setMessage(message)
            .setPositiveButton("OK") { _,_ ->
                //nothing

            }
            .show()
    }

    private fun resetForm() {

        var message = "Name: " + binding.nameEditText.text
            message += "\nAge: " + binding.ageEditText.text
            message += "\nIQ : " + binding.iqTestEditText.text

        AlertDialog.Builder(this)
            .setTitle("Form Submit")
            .setMessage(message)
            .setPositiveButton("OK") { _,_ ->
                binding.nameEditText.text = null
                binding.ageEditText.text = null
                binding.iqTestEditText.text = null
            }
            .show()

    }

    private fun nameFocusListener() {
        binding.nameEditText.setOnFocusChangeListener{_, focus ->
            if (!focus){
                binding.nameContainer.helperText.validName()
            }
        }
    }

}

private fun Any?.validName() {
}


