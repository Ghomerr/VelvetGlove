package com.ghomerr.velvetglove;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;


public class VelvetGlove extends JavaPlugin
{
	private boolean _pluginsEnabled = false;
	private Material _velvetGloveMaterial = null;
	private boolean _targetPlayers = true;
	private boolean _targetOthers = true;
	
	public static final String VELVET_TAG = "[VelvetGlove] ";
	public static final String VELVETGLOVE = "velvetglove";
	public static final String TARGETS_PLAYERS = "targets.players";
	public static final String TARGETS_OTHERS = "targets.others";
	
	public EventsListener eventsListener = null;
	
	@Override
	public void onEnable()
	{
		super.onEnable();
		
		_targetPlayers = getConfig().getBoolean(TARGETS_PLAYERS, true);
		_targetOthers = getConfig().getBoolean(TARGETS_OTHERS, true);
		
		final String strMaterial = getConfig().getString(VELVETGLOVE);
		if (!setVelvetGloveMaterial(strMaterial))
		{
			_velvetGloveMaterial = Material.SNOW_BALL;
			saveConfig();
			getLogger().warning(VELVET_TAG + "Wrong configuration for velvetglove=" + strMaterial + ". Using default value : SNOW_BALL");
		}
		else
		{
			saveConfig();
		}
		
		this.getCommand(VELVETGLOVE).setExecutor(new CommandsExecutor(this));
		eventsListener = new EventsListener(this);
	}

	@Override
	public void onDisable()
	{
		super.onDisable();
	}

	public boolean isPluginEnabled()
	{
		return _pluginsEnabled;
	}
	
	public void setPluginEnabled(final boolean state)
	{
		_pluginsEnabled = state;
	}
	
	public Material getVelvetGloveMaterial()
	{
		return _velvetGloveMaterial;
	}
	
	public boolean setVelvetGloveMaterial(final String strMaterial)
	{
		if (strMaterial != null && !strMaterial.isEmpty())
		{
			try
			{
				final String upperCaseMaterial = strMaterial.toUpperCase();
				_velvetGloveMaterial = Material.valueOf(upperCaseMaterial);
				getConfig().set(VELVETGLOVE, upperCaseMaterial);
				saveConfig();
				return true;
			}
			catch (final Throwable th)
			{
				getLogger().severe(VELVET_TAG + "Unknown material: " + strMaterial);
				th.printStackTrace();
			}
		}
		return false;
	}
	
	public void setPlayersTargeted(final String bool)
	{
		_targetPlayers = Boolean.parseBoolean(bool);
		getConfig().set(TARGETS_PLAYERS, _targetPlayers);
		saveConfig();
	}
	
	public boolean isPlayersTargeted()
	{
		return _targetPlayers;
	}
	
	public void setOthersTargeted(final String bool)
	{
		_targetOthers = Boolean.parseBoolean(bool.toLowerCase());
		getConfig().set(TARGETS_OTHERS, _targetOthers);
		saveConfig();
	}
	
	public boolean isOthersTargeted()
	{
		return _targetOthers;
	}
}
