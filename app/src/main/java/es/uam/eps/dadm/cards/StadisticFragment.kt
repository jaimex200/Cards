package es.uam.eps.dadm.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import es.uam.eps.dadm.cards.databinding.FragmentStadisticBinding
import es.uam.eps.dadm.cards.src.Stadistics


class StadisticFragment: Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentStadisticBinding>(
            inflater,
            R.layout.fragment_stadistic,
            container,
            false
        )
        var stats = Stadistics.getThis()

        var data = mutableListOf<PieEntry>()
        data.add(PieEntry(stats.success.toFloat(), "Success"))
        data.add(PieEntry(stats.doubt.toFloat(), "Doubt"))
        data.add(PieEntry(stats.error.toFloat(), "Errors"))

        var dataSet = PieDataSet(data, "Results")

        var colors = mutableListOf<Int>()
        for (color in ColorTemplate.MATERIAL_COLORS){
            colors.add(color)
        }
        dataSet.colors = colors


        var pieData = PieData(dataSet)

        var pieChart = binding.pieChartStat
        pieChart.data = pieData
        pieChart.invalidate()

        return binding.root
    }
}