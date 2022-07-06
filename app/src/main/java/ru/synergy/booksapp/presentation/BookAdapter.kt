package ru.synergy.booksapp.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.synergy.booksapp.R
import ru.synergy.booksapp.entities.BookWithStatus
import ru.synergy.booksapp.entities.BookmarkStatus


class BookAdapter(
    private val context: Context,
    private val listener: ActionClickListener//извне создаем листенер и передаем его в адаптер
) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    private val books: ArrayList<BookWithStatus> = arrayListOf()//сохр лист книг

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {  //возвращаем новый вью холдер
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_book,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        books[position].also { book ->//берем букс по позишн который передали, говорим что в том числе
            book.imageUrl?.let { imageUrl ->// проверяем нулл там или нет, если нет
                Glide.with(context)
                    .load(imageUrl)
                    .into(holder.ivBookCover)// через глайд выкачиваем
                holder.tvBookName.text = "" // текст пустой
                holder.tvBookAuthors.text = "" // текст пустой
            } ?: kotlin.run { // если да то запускаем логику
                Glide.with(context)//получаем картинку
                    .load(R.drawable.ic_launcher_background)
                    .into(holder.ivBookCover)// если картинку не загрузили - ставим дефолтную заглушку
                holder.tvBookName.text = book.title// выставляем поле твбукнэйм
                holder.tvBookAuthors.text = book.authors.joinToString()// массив авторов собир в одну стрингу
                //и выдаем текст на наше поле
            }

            holder.ivUnbookmark.setOnClickListener {
                listener.unbookmark(book)// нам передвют листнер и мы его пробиваем
            }

            holder.ivBookmark.setOnClickListener {
                listener.bookmark(book)
            }

            when (book.status) {//закладки книжек
                BookmarkStatus.BOOKMARKED -> {
                    holder.ivBookmark.visibility = View.GONE
                    holder.ivUnbookmark.visibility = View.VISIBLE
                }
                BookmarkStatus.UNBOOKMARKED -> {
                    holder.ivBookmark.visibility = View.VISIBLE
                    holder.ivUnbookmark.visibility = View.GONE
                }
            }
        }
    }

    fun submitUpdate(update: List<BookWithStatus>) {
        val callback = BooksDiffCallback(books, update)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(callback)

        books.clear()
        books.addAll(update)
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {//все валы перепресваиваем
        val tvBookName: TextView = view.findViewById(R.id.tvBookName)
        val tvBookAuthors: TextView = view.findViewById(R.id.tvBookAuthors)
        val ivBookCover: ImageView = view.findViewById(R.id.ivBookCover)
        val ivBookmark: ImageView = view.findViewById(R.id.ivBookmark)
        val ivUnbookmark: ImageView = view.findViewById(R.id.ivUnbookmark)
    }

    class BooksDiffCallback(//проверяет разницу между данными
        private val oldBooks: List<BookWithStatus>,
        private val newBooks: List<BookWithStatus>
    ) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldBooks.size
        }

        override fun getNewListSize(): Int {
            return newBooks.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {//провер равность элементов
            return oldBooks[oldItemPosition].id == newBooks[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {//проверяем разницу в контенте
            return oldBooks[oldItemPosition].status == newBooks[newItemPosition].status
        }
    }

    interface ActionClickListener {//самодокументирующих интерфейс то что должны передать нашему адаптеру  как листенер
        fun bookmark(book: BookWithStatus)
        fun unbookmark(book: BookWithStatus)
    }
}