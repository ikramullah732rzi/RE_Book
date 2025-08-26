package com.example.re_book

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.re_book.data.Model
import com.example.re_book.data.RepoImp
import com.example.re_book.presentation.navigations.App
import com.example.re_book.ui.theme.REBookTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var repoImp: RepoImp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       installSplashScreen()
        enableEdgeToEdge()
       /* val categories = listOf(
            "Science & Technology 🔬",
            "Fiction & Literature 📖",
            "Biographies & Memoirs 👤",
            "History & Politics 🏛️",
            "Self-Help & Personal Development 🌱",
            "Education & Academics 🎓",
            "Business & Economics 💼",
            "Health & Fitness 🏃‍♂️",
            "Religion & Spirituality ✨",
            "Children & Young Adult 🧒📚"
        )*/
        val books = listOf(
            Model(
                id = UUID.randomUUID().toString(),
                bookName = "Introduction to Computer Science",
                bookData = "https://ocw.mit.edu/cs_intro.pdf",
                createDate = "8/24/2025",
                bookCategory = "Science & Technology 🔬"
            ),
            Model(
                id = UUID.randomUUID().toString(),
                bookName = "Pride and Prejudice",
                bookData = "https://www.gutenberg.org/files/1342/1342-h/1342-h.htm",
                createDate = "8/24/2025",
                bookCategory = "Fiction & Literature 📖"
            ),
            Model(
                id = UUID.randomUUID().toString(),
                bookName = "The Story of My Life - Helen Keller",
                bookData = "https://www.gutenberg.org/files/2397/2397-h/2397-h.htm",
                createDate = "8/24/2025",
                bookCategory = "Biographies & Memoirs 👤"
            ),
            Model(
                id = UUID.randomUUID().toString(),
                bookName = "World History Overview",
                bookData = "https://ocw.mit.edu/history.pdf",
                createDate = "8/24/2025",
                bookCategory = "History & Politics 🏛️"
            ),
            Model(
                id = UUID.randomUUID().toString(),
                bookName = "Atomic Habits",
                bookData = "https://example.com/atomic_habits.pdf",
                createDate = "8/24/2025",
                bookCategory = "Self-Help & Personal Development 🌱"
            ),
            Model(
                id = UUID.randomUUID().toString(),
                bookName = "Mathematics for Beginners",
                bookData = "https://ocw.mit.edu/math101.pdf",
                createDate = "8/24/2025",
                bookCategory = "Education & Academics 🎓"
            ),
            Model(
                id = UUID.randomUUID().toString(),
                bookName = "Principles of Economics",
                bookData = "https://ocw.mit.edu/economics.pdf",
                createDate = "8/24/2025",
                bookCategory = "Business & Economics 💼"
            ),
            Model(
                id = UUID.randomUUID().toString(),
                bookName = "The Fitness Guide",
                bookData = "https://example.com/fitness.pdf",
                createDate = "8/24/2025",
                bookCategory = "Health & Fitness 🏃‍♂️"
            ),
            Model(
                id = UUID.randomUUID().toString(),
                bookName = "The Holy Quran",
                bookData = "https://quran.com",
                createDate = "8/24/2025",
                bookCategory = "Religion & Spirituality ✨"
            ),
            Model(
                id = UUID.randomUUID().toString(),
                bookName = "Harry Potter and the Sorcerer’s Stone",
                bookData = "https://example.com/harrypotter1.pdf",
                createDate = "8/24/2025",
                bookCategory = "Children & Young Adult 🧒📚"
            ),
            Model(
                id = UUID.randomUUID().toString(),
                bookName = "Artificial Intelligence Basics",
                bookData = "https://ocw.mit.edu/ai_basics.pdf",
                createDate = "8/24/2025",
                bookCategory = "Science & Technology 🔬"
            ),
            Model(
                id = UUID.randomUUID().toString(),
                bookName = "Great Expectations",
                bookData = "https://www.gutenberg.org/files/1400/1400-h/1400-h.htm",
                createDate = "8/24/2025",
                bookCategory = "Fiction & Literature 📖"
            ),
            Model(
                id = UUID.randomUUID().toString(),
                bookName = "Steve Jobs Biography",
                bookData = "https://example.com/steve_jobs.pdf",
                createDate = "8/24/2025",
                bookCategory = "Biographies & Memoirs 👤"
            ),
            Model(
                id = UUID.randomUUID().toString(),
                bookName = "The Second World War",
                bookData = "https://example.com/ww2.pdf",
                createDate = "8/24/2025",
                bookCategory = "History & Politics 🏛️"
            ),
            Model(
                id = UUID.randomUUID().toString(),
                bookName = "Deep Work",
                bookData = "https://example.com/deep_work.pdf",
                createDate = "8/24/2025",
                bookCategory = "Self-Help & Personal Development 🌱"
            )
        )


               /* books.forEachIndexed { index, s ->
                    repoImp.createDB(s)
                }*/

        setContent {
            REBookTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)){
                      App()
                    }
                }
            }
        }
    }
}
