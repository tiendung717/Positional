package app.simple.positional.dialogs.settings

import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import app.simple.positional.R
import app.simple.positional.activities.alias.*
import app.simple.positional.decorations.views.CustomBottomSheetDialogFragment
import app.simple.positional.decorations.views.CustomRadioButton

class Icons : CustomBottomSheetDialogFragment() {

    private lateinit var iconOne: CustomRadioButton
    private lateinit var iconTwo: CustomRadioButton
    private lateinit var iconThree: CustomRadioButton
    private lateinit var iconFour: CustomRadioButton
    private lateinit var iconFive: CustomRadioButton
    private lateinit var iconSix: CustomRadioButton

    fun newInstance(): Icons {
        val args = Bundle()
        val fragment = Icons()
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_icon_selection, container, false)

        iconOne = view.findViewById(R.id.icon_one_radio_button)
        iconTwo = view.findViewById(R.id.icon_two_radio_button)
        iconThree = view.findViewById(R.id.icon_three_radio_button)
        iconFour = view.findViewById(R.id.icon_four_radio_button)
        iconFive = view.findViewById(R.id.icon_five_radio_button)
        iconSix = view.findViewById(R.id.icon_six_radio_button)

        setButtons(getIconStatus())

        iconOne.setOnClickListener {
            setButtons(1)
            setIcon()
        }

        iconTwo.setOnClickListener {
            setButtons(2)
            setIcon()
        }

        iconThree.setOnClickListener {
            setButtons(3)
            setIcon()
        }

        iconFour.setOnClickListener {
            setButtons(4)
            setIcon()
        }

        iconFive.setOnClickListener {
            setButtons(5)
            setIcon()
        }

        iconSix.setOnClickListener {
            setButtons(6)
            setIcon()
        }

        return view
    }

    private fun getIconStatus(): Int {
        return when (PackageManager.COMPONENT_ENABLED_STATE_ENABLED) {
            requireActivity().packageManager.getComponentEnabledSetting(ComponentName(requireActivity(), IconOneAlias::class.java)) -> {
                1
            }
            requireActivity().packageManager.getComponentEnabledSetting(ComponentName(requireActivity(), IconTwoAlias::class.java)) -> {
                2
            }
            requireActivity().packageManager.getComponentEnabledSetting(ComponentName(requireActivity(), IconThreeAlias::class.java)) -> {
                3
            }
            requireActivity().packageManager.getComponentEnabledSetting(ComponentName(requireActivity(), IconFourAlias::class.java)) -> {
                4
            }
            requireActivity().packageManager.getComponentEnabledSetting(ComponentName(requireActivity(), IconFiveAlias::class.java)) -> {
                5
            }
            requireActivity().packageManager.getComponentEnabledSetting(ComponentName(requireActivity(), IconSixAlias::class.java)) -> {
                6
            }
            else -> {
                1
            }
        }
    }

    private fun setButtons(value: Int) {
        iconOne.isChecked = value == 1
        iconTwo.isChecked = value == 2
        iconThree.isChecked = value == 3
        iconFour.isChecked = value == 4
        iconFive.isChecked = value == 5
        iconSix.isChecked = value == 6
    }

    private fun setIcon() {
        requireActivity().packageManager.setComponentEnabledSetting(ComponentName(requireActivity(), IconOneAlias::class.java), getStatusFromButton(iconOne), PackageManager.DONT_KILL_APP)
        requireActivity().packageManager.setComponentEnabledSetting(ComponentName(requireActivity(), IconTwoAlias::class.java), getStatusFromButton(iconTwo), PackageManager.DONT_KILL_APP)
        requireActivity().packageManager.setComponentEnabledSetting(ComponentName(requireActivity(), IconThreeAlias::class.java), getStatusFromButton(iconThree), PackageManager.DONT_KILL_APP)
        requireActivity().packageManager.setComponentEnabledSetting(ComponentName(requireActivity(), IconFourAlias::class.java), getStatusFromButton(iconFour), PackageManager.DONT_KILL_APP)
        requireActivity().packageManager.setComponentEnabledSetting(ComponentName(requireActivity(), IconFiveAlias::class.java), getStatusFromButton(iconFive), PackageManager.DONT_KILL_APP)
        requireActivity().packageManager.setComponentEnabledSetting(ComponentName(requireActivity(), IconSixAlias::class.java), getStatusFromButton(iconSix), PackageManager.DONT_KILL_APP)
    }

    private fun getStatusFromButton(radioButton: RadioButton): Int {
        return if (radioButton.isChecked) {
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED
        } else {
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED
        }
    }
}
