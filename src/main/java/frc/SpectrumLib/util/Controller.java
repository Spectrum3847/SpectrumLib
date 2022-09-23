package frc.SpectrumLib.util;

public abstract class Controller {
    protected boolean m_enabled = false;

    public abstract void reset();

    public abstract boolean isOnTarget();
}
