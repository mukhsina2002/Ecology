package uz.salikhdev.ecology.ui.plant

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.salikhdev.ecology.R
import uz.salikhdev.ecology.core.adapter.PlantAdapter
import uz.salikhdev.ecology.core.alarm.AlarmReceiver
import uz.salikhdev.ecology.core.base.BaseFragment
import uz.salikhdev.ecology.core.cache.LocalStorage
import uz.salikhdev.ecology.core.util.gone
import uz.salikhdev.ecology.core.util.visible
import uz.salikhdev.ecology.databinding.ScreenPlantBinding


class PlantScreen : BaseFragment(R.layout.screen_plant) {

    private val binding by viewBinding(ScreenPlantBinding::bind)
    private val viewModel: PlantViewModel by viewModels()
    private val adapter by lazy { PlantAdapter() }
    private var alarmManager: AlarmManager? = null
    private var localStorage: LocalStorage? = null

    override fun onViewCreated() {
        localStorage = LocalStorage(requireContext())

        setAdapter()
        observer()
        loadAciton()
        loadNotification()


    }

    private fun loadAciton() {

        binding.switchBtn.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setAlarm()
                localStorage?.notificationOn = true
            } else {
                clearAlarm()
                localStorage?.notificationOn = false
            }
        }

    }

    private fun loadNotification() {

        localStorage?.let {
            if (it.notificationOn) {
                setAlarm()
                binding.switchBtn.isChecked = true
                Log.d("TAGaaa", "loadNotification: true")
            } else {
                clearAlarm()
                binding.switchBtn.isChecked = false
                Log.d("TAGaaa", "loadNotification: false")
            }
        }

    }

    private fun setAlarm() {

        /*  val calendar = Calendar.getInstance()
          calendar.timeInMillis = System.currentTimeMillis()
          calendar.set(Calendar.HOUR, 13)
          calendar.set(Calendar.MINUTE, 5)


          alarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager?
          val intent = Intent(context, AlarmReceiver::class.java)
          val pendingIntent =
              PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_MUTABLE)
          alarmManager?.setInexactRepeating(
              AlarmManager.RTC_WAKEUP,
              calendar.timeInMillis,
              AlarmManager.INTERVAL_DAY,
              pendingIntent
          )*/
        /* val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager

         val intent = Intent(requireActivity(), AlarmReceiver::class.java)
         val pendingIntent =
             PendingIntent.getBroadcast(
                 requireActivity(),
                 0,
                 intent,
                 PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
             )

         val calendar = Calendar.getInstance().apply {
             timeInMillis = System.currentTimeMillis()
             set(Calendar.HOUR, 13)
             set(Calendar.MINUTE, 45)
         }

         if (calendar.timeInMillis <= System.currentTimeMillis()) {
             calendar.add(Calendar.DAY_OF_YEAR, 1)
         }

         alarmManager.set(
             AlarmManager.RTC_WAKEUP,
             calendar.timeInMillis,
             pendingIntent
         )*/


        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 14)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }

        val intent = Intent(requireContext(), AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(),
            0,
            intent,
            PendingIntent.FLAG_MUTABLE
        )

        val alarmManager = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)


    }

    private fun clearAlarm() {
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        if (alarmManager == null) {
            alarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager?
        }
        alarmManager?.cancel(pendingIntent)
    }

    private fun setAdapter() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        adapter.onClickItem = {
            findNavController().navigate(
                PlantScreenDirections.actionPlantScreenToPlantDetailScreen(
                    it
                )
            )
        }

    }

    private fun observer() {
        binding.progressBar.visible()
        viewModel.plantLD.observe(viewLifecycleOwner) {
            adapter.setData(it)
            binding.progressBar.gone()
        }
        viewModel.getPlants(localStorage!!.language)
    }


}