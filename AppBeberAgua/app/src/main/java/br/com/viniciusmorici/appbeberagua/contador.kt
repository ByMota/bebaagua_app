package br.com.viniciusmorici.appbeberagua

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_categorias.*
import kotlinx.android.synthetic.main.activity_contador.*

class contador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contador)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        val args = intent.extras
        var idBtn = args?.getInt("id_btn")
        var addMl = 0
        if (idBtn == 1){
            val litragem = args?.getString("500ml")
            txtLitragem.text = litragem
            btnAdd.setOnClickListener {
                addMl += 500
                txtCont.text = addMl.toString()
                if (txtCont.text.toString().toInt() >= 1000){
                    txtMedidor.text = "L"

                                    }
            }
        }else if (idBtn == 2){
            val litragem = args?.getString("700ml")
            txtLitragem.text = litragem
            btnAdd.setOnClickListener {
                addMl += 700
                txtCont.text = addMl.toString()
                if (txtCont.text.toString().toInt() >= 1000){
                    txtMedidor.text = "L"
                }
            }
        }else if (idBtn == 3){
            val litragem = args?.getString("1l")
            txtLitragem.text = litragem
            btnAdd.setOnClickListener {
                addMl += 1000
                txtCont.text = addMl.toString()
                if (txtCont.text.toString().toInt() >= 1000){
                    txtMedidor.text = "L"
                }
            }
        }

        val params = Bundle()
        intent.putExtras(params)
        var totalCont = txtCont.text.toString().toInt()
        params.putInt("totalCont", totalCont)
        intent.putExtras(params)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            val intent = Intent(this, categorias::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}