//Spectrum 3847
//Based on the team254 frc2015 code.
package frc.SpectrumLib.drivers;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;


public class SpectrumSolenoid extends Solenoid {
    private boolean m_on = false;
    private boolean m_was_disabled = true;

    // Allen - Allows the use of 0-13 to set solenoid number, ports 0-7 on module 2
    // or solenoids 8-13.
    public SpectrumSolenoid(int channel) {
        super((channel > 7 ? 1 : 0), PneumaticsModuleType.CTREPCM, (channel > 7 ? channel - 8 : channel));
    }

        // Allen - Allows the use of 0-13 to set solenoid number, ports 0-7 on module 2
    // or solenoids 8-13.
    public SpectrumSolenoid(final PneumaticsModuleType moduleType, int channel) {
        super(moduleType, channel);
    }

    @Override
    // Allen - Only update solenoid if we need to send a new command or the robot
    // state changed from or to Disabled
    public void set(boolean value) {
        boolean is_disabled = DriverStation.isDisabled();
        if ((!is_disabled && m_was_disabled) || value != m_on) {
            super.set(value);
        }
        m_on = value;
        m_was_disabled = is_disabled;
    }

    @Override
    // Allen - Don't waste time with a CAN command to check the state
    public boolean get() {
        return m_on;
    }
}
