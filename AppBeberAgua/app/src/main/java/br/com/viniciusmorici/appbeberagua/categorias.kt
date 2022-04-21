package br.com.viniciusmorici.appbeberagua

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_categorias.*

class categorias : AppCompatActivity() {
    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorias)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val params = Bundle()
        var idBtn = 0

        btn500ml.setOnClickListener {
            idBtn = 1
            val intent = Intent(this, contador::class.java)
            params.putString("500ml", "500ml")
            params.putInt("id_btn", idBtn)
            intent.putExtras(params)
            startActivity(intent)
            finish()
        }
        btn700ml.setOnClickListener {
            idBtn = 2
            val intent = Intent(this, contador::class.java)
            params.putString("700ml", "700ml")
            params.putInt("id_btn", idBtn)
            intent.putExtras(params)
            startActivity(intent)
            finish()
        }
        btn1l.setOnClickListener {
            idBtn = 3
            val intent = Intent(this, contador::class.java)
            params.putString("1l", "1Litro")
            params.putInt("id_btn", idBtn)
            intent.putExtras(params)
            startActivity(intent)
            finish()
        }

        val args = intent.extras
        var total = args?.getInt("totalCont", 0)
        txtTotal.text = total.toString()



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}


