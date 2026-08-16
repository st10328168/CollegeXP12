package com.example.collegexp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val txtPaymentEvent =
            findViewById<TextView>(R.id.txtPaymentEvent)

        val txtPaymentUniversity =
            findViewById<TextView>(R.id.txtPaymentUniversity)

        val txtPaymentQuantity =
            findViewById<TextView>(R.id.txtPaymentQuantity)

        val txtPaymentTotal =
            findViewById<TextView>(R.id.txtPaymentTotal)

        val edtCardNumber =
            findViewById<EditText>(R.id.edtCardNumber)

        val edtExpiry =
            findViewById<EditText>(R.id.edtExpiry)

        val edtCVV =
            findViewById<EditText>(R.id.edtCVV)

        val edtCardName =
            findViewById<EditText>(R.id.edtCardName)

        val btnPayNow =
            findViewById<Button>(R.id.btnPayNow)

        val txtPaymentStatus =
            findViewById<TextView>(R.id.txtPaymentStatus)

        // Receive information from CartActivity

        val eventName =
            intent.getStringExtra("eventName")

        val university =
            intent.getStringExtra("university")

        val quantity =
            intent.getIntExtra("quantity", 1)

        val total =
            intent.getDoubleExtra("total", 50.00)

        // Display information

        txtPaymentEvent.text =
            eventName ?: "Event"

        txtPaymentUniversity.text =
            university ?: "University"

        txtPaymentQuantity.text =
            if (quantity == 1) {
                "1 ticket"
            } else {
                "$quantity tickets"
            }

        txtPaymentTotal.text =
            "R%.2f".format(total)

        // Pay button

        btnPayNow.setOnClickListener {

            val cardNumber =
                edtCardNumber.text.toString().trim()

            val expiry =
                edtExpiry.text.toString().trim()

            val cvv =
                edtCVV.text.toString().trim()

            val cardName =
                edtCardName.text.toString().trim()

            // Basic validation

            if (cardNumber.isEmpty()) {
                edtCardNumber.error = "Enter card number"
                return@setOnClickListener
            }

            if (cardNumber.length < 16) {
                edtCardNumber.error = "Enter a valid card number"
                return@setOnClickListener
            }

            if (expiry.isEmpty()) {
                edtExpiry.error = "Enter expiry date"
                return@setOnClickListener
            }

            if (cvv.length < 3) {
                edtCVV.error = "Enter valid CVV"
                return@setOnClickListener
            }

            if (cardName.isEmpty()) {
                edtCardName.error = "Enter name on card"
                return@setOnClickListener
            }

            // Temporary local payment confirmation.
            // A real payment gateway will replace this later.

            txtPaymentStatus.text =
                "Payment successful!\nYour ticket has been purchased."

            btnPayNow.text = "PAYMENT COMPLETE"
            btnPayNow.isEnabled = false
        }
    }
}