package ru.cft.movieapp.ui.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import okhttp3.internal.notifyAll
import ru.cft.movieapp.R
import ru.cft.movieapp.databinding.FragmentMainBinding
import ru.cft.movieapp.models.MovieItemModel
import ru.cft.movieapp.util.ContentModel
import ru.cft.movieapp.util.NoCrashLinearLayoutManager

@AndroidEntryPoint
class MainFragment : Fragment(), MenuProvider {

    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Cannot access view")
    private var content = mutableListOf<ContentModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMovies()
        initTv()
    }

    private fun initMovies() {
        viewModel.getInfo()
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.result.collect { movies ->
                delay(500)
                if (movies != null) {
                    content.add(ContentModel("Popular movies", movies.results))
                }
            }
        }
    }

    private fun initTv() {
        viewModel.getInfoTv()
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.resultTv.collect { tv ->
                delay(1000)
                if (tv != null) {
                        content.add(ContentModel("Popular TV shows", tv.results))
                        val adapter = MainAdapter(content)
                        binding.rvListPopular.layoutManager = NoCrashLinearLayoutManager()
                        binding.rvListPopular.adapter = adapter
                        binding.rvListPopular.itemAnimator = null
                }
            }
        }
    }

    companion object {
        fun clickMovie(model: MovieItemModel, view: View) {
            val bundle = Bundle()
            bundle.putSerializable("key", model)
            Navigation.createNavigateOnClickListener(
                R.id.action_mainFragment_to_detailsFragment,
                bundle
            ).onClick(view)
        }
    }

    override fun onStop() {
        content.clear()
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.favorite_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }
}