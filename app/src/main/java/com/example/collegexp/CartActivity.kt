package com.example.collegexp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CartActivity : AppCompatActivity() {

    private var quantity = 1

    // Ticket price for now
    private val ticketPrice = 50.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        // Views
        val imgCartPoster = findViewById<ImageView>(R.id.imgCartPoster)

        val txtCartEvent = findViewById<TextView>(R.id.txtCartEvent)
        val txtCartUniversity = findViewById<TextView>(R.id.txtCartUniversity)
        val txtCartTime = findViewById<TextView>(R.id.txtCartTime)
        val txtCartTicketPrice = findViewById<TextView>(R.id.txtCartTicketPrice)

        val btnMinus = findViewById<Button>(R.id.btnMinus)
        val btnPlus = findViewById<Button>(R.id.btnPlus)

        val txtQuantity = findViewById<TextView>(R.id.txtQuantity)
        val txtSubtotal = findViewById<TextView>(R.id.txtSubtotal)
        val txtTotal = findViewById<TextView>(R.id.txtTotal)

        val btnProceedPayment = findViewById<Button>(R.id.btnProceedPayment)
        val btnCartRSVP = findViewById<Button>(R.id.btnCartRSVP)

        // Receive event information
        val eventName = intent.getStringExtra("eventName")
        val university = intent.getStringExtra("university")
        val time = intent.getStringExtra("time")

        val poster = intent.getIntExtra(
            "poster",
            R.drawable.event1
        )

        // Display event information
        txtCartEvent.text = eventName ?: "Event"
        txtCartUniversity.text = university ?: "University"
        txtCartTime.text = time ?: "Time not available"
        txtCartTicketPrice.text = "R%.2f per ticket".format(ticketPrice)

        imgCartPoster.setImageResource(poster)

        // Update total
        fun updateTotal() {

            val subtotal = quantity * ticketPrice

            txtQuantity.text = quantity.toString()
            txtSubtotal.text = "R%.2f".format(subtotal)
            txtTotal.text = "R%.2f".format(subtotal)
        }

        // Minus button
        btnMinus.setOnClickListener {

            if (quantity > 1) {
                quantity--
                updateTotal()
            }
        }

        // Plus button
        btnPlus.setOnClickListener {

            if (quantity < 10) {
                quantity++
                updateTotal()
            }
        }

        // Initial total
        updateTotal()

        // Proceed to payment
        btnProceedPayment.setOnClickListener {

            val paymentIntent = Intent(
                this,
                PaymentActivity::class.java
            )

            paymentIntent.putExtra("eventName", eventName)
            paymentIntent.putExtra("university", university)
            paymentIntent.putExtra("time", time)
            paymentIntent.putExtra("poster", poster)
            paymentIntent.putExtra("quantity", quantity)
            paymentIntent.putExtra("total", quantity * ticketPrice)

            startActivity(paymentIntent)
        }

        // RSVP instead
        btnCartRSVP.setOnClickListener {

            btnCartRSVP.text = "RSVP CONFIRMED"
            btnCartRSVP.isEnabled = false
        }
    }
}