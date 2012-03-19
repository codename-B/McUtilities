package net.milkycraft.Configuration;

import org.bukkit.configuration.file.YamlConfigurationOptions;

public class GoldConfigurationOptions extends YamlConfigurationOptions {
	public GoldConfigurationOptions(GoldConfiguration configuration){
		super(configuration);
	}
	@Override
	public GoldConfiguration configuration(){
		return (GoldConfiguration) super.configuration();
	}
	@Override
	public GoldConfigurationOptions copyDefaults(boolean value){
		super.copyDefaults(value);
		return this;
	}
	@Override
	public GoldConfigurationOptions header(String value){
		super.header(value);
		return this;
	}
	//    /**
	//     * Allows case-insensitive lookups
	//     *
	//     * @param value True to turn off sensitivity
	//     * @return This Instance
	//     */
	//    public GoldConfigurationOptions lowercaseKeys(boolean value) {
	//        lowercaseKeys = value;
	//        return this;
	//    }
	//
	//    public boolean lowercaseKeys() {
	//        return lowercaseKeys;
	//    }

	/**
	 * 
	 * 
	 * @param lines Comma Separated strings to build into the header
	 * @return This Instance
	 */
	public GoldConfigurationOptions header(String... lines){
		StringBuilder string = new StringBuilder();
		for(String s : lines){
			if(s == null)
				continue;
			if(string.length() > 0){
				string.append("\n");
			}
			string.append(s);
		}
		header(string.length() == 0 ? null : string.toString());
		return this;
	}
	@Override
	public GoldConfigurationOptions copyHeader(boolean value){
		super.copyHeader(value);
		return this;
	}
	@Override
	public GoldConfigurationOptions pathSeparator(char value){
		super.pathSeparator(value);
		return this;
	}
	@Override
	public GoldConfigurationOptions indent(int value){
		super.indent(value);
		return this;
	}
}