package com.example.notes

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(), OnClickItem {
    lateinit var viewModel: NotesViewModel
    lateinit var rvNotes: RecyclerView
    lateinit var input: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NotesViewModel::class.java)
        rvNotes = findViewById(R.id.rvNotes)
        input = findViewById(R.id.input)
        rvNotes.layoutManager = LinearLayoutManager(this)
        val adapter = NotesAdapter(this, this)
        rvNotes.adapter = adapter
        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(it)
            }

        })

    }

    override fun onItemClick(notes: Notes) {
        viewModel.deleteNote(notes)
        Toast.makeText(this, "${notes.text} Deleted ", Toast.LENGTH_SHORT).show()
    }

    fun submitData(view: View) {
        val noteText = input.text.toString()
        if (noteText.isNotEmpty()) {
            viewModel.insertNote(Notes(noteText))
            Toast.makeText(this, "$noteText Inserted ", Toast.LENGTH_SHORT).show()
            input.text.clear()
        }
    }
}