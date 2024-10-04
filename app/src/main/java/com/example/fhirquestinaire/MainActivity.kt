package com.example.covid19registration

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.fhirquestinaire.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // References to UI elements
        val firstName = findViewById<EditText>(R.id.firstName)
        val middleName = findViewById<EditText>(R.id.middleName)
        val lastName = findViewById<EditText>(R.id.lastName)
        val age = findViewById<EditText>(R.id.age)
        val dob = findViewById<EditText>(R.id.dob)
        val genderGroup = findViewById<RadioGroup>(R.id.genderGroup)
        val adverseEffect = findViewById<CheckBox>(R.id.adverseEffect)
        val submitBtn = findViewById<Button>(R.id.submitBtn)

        // Spinner setup for vaccine type
        val vaccineSpinner = findViewById<Spinner>(R.id.vaccineSpinner)
        val vaccineTypes = arrayOf("Pfizer", "Moderna", "AstraZeneca")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, vaccineTypes)
        vaccineSpinner.adapter = adapter

        // Submit button logic
        submitBtn.setOnClickListener {
            // Get values from input fields
            val fName = firstName.text.toString()
            val mName = middleName.text.toString()
            val lName = lastName.text.toString()
            val userAge = age.text.toString()
            val userDob = dob.text.toString()

            // Get selected gender
            val selectedGenderId = genderGroup.checkedRadioButtonId
            val selectedGender = findViewById<RadioButton>(selectedGenderId)?.text.toString()

            // Get vaccine type
            val selectedVaccine = vaccineSpinner.selectedItem.toString()

            // Check if adverse effect was selected
            val hasAdverseEffect = adverseEffect.isChecked

            // Display the collected information
            val summary = """
                Name: $fName $mName $lName
                Age: $userAge
                Date of Birth: $userDob
                Gender: $selectedGender
                Vaccine Type: $selectedVaccine
                Adverse Effect: $hasAdverseEffect
            """.trimIndent()

            // Display the result in a Toast
            Toast.makeText(this, summary, Toast.LENGTH_LONG).show()

            // Handle adverse effect logic (e.g., hospital referral)
            if (hasAdverseEffect) {
                Toast.makeText(this, "Please visit a hospital for further checkup.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
