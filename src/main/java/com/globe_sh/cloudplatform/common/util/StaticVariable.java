package com.globe_sh.cloudplatform.common.util;

public class StaticVariable {

	public static final String DEFAULT_NONE = "N/A";
	public static String REDIS_IP = "127.0.0.1";
	public static String LOCAL_IP = "";
	public static final int REDIS_PORT = 6379;
	
	public static final int REDIS_TABLE_AGENT_LIST = 0; 			//agent列表
	
	public static final int REDIS_TABLE_STATION_LIST = 1;     		//Contain the station table，key is station code
	public static final int REDIS_TABLE_STATION_ONLINE = 2;   		//Contain the station status，key is station code
	public static final int REDIS_TABLE_STATION_PROTOCOL = 3; 		//Contains the data decoder, the key is the data block code
	public static final int REDIS_TABLE_STATION_STATUS = 4; 		//Contains the station data, key is the station code
	public static final int REDIS_TABLE_DATABLOCK_INDEX = 5; 		//Contains the datablock index and code table

	public static final int REDIS_TABLE_AGENT_CLUSTER = 4;
	//header
	public static final int PROTOCOL_CONTROL_LENGTH = 25;
	public static final int PROTOCOL_CONTROL_STATION_START = 6;
	public static final int PROTOCOL_CONTROL_STATION_LENGTH = 8;	
	public static final int PROTOCOL_CONTROL_DATALENGTH_START = 22;
	public static final int PROTOCOL_CONTROL_DATATIME_START = 14;
	public static final int PROTOCOL_CONTROL_DATATIME_LENGTH = 8;
	//01 login
	public static final int PROTOCOL_CONTROL_LOGINSEQ_START = 24;
	public static final int PROTOCOL_CONTROL_NUMBER_START = 26;
	//02 data
	public static final int PROTOCOL_CONTROL_DEVICE_START = 24;
	public static final int PROTOCOL_CONTROL_DEVICE_LENGTH = 8;
	public static final int PROTOCOL_CONTROL_DB_START = 34;
	public static final int PROTOCOL_CONTROL_DB_LENGTH = 8;	
	public static final int PROTOCOL_CONTROL_SAMPLETIME_START = 42;	
	public static final int PROTOCOL_CONTROL_REALDATALENGTH_START = 50;
	public static final int PROTOCOL_CONTROL_DATA_START = 52;
	
	public static final byte PROTOCOL_LOGIN = 0x01;
	public static final byte PROTOCOL_LOGOUT = 0x04;
	
	public static final int DATA_LENGTH_START = 22;
	public static final int CONTROL_DATA_LENGTH = 25;
	public static final int STATION_ID_LENGTH = 8;
	public static final int STATION_ID_START = 6;
	
	public static final String OPERATE_TYPE_QUERY = "Query";
	public static final String OPERATE_TYPE_SETUP = "Setup";
	public static final String OPERATE_TYPE_LOCK = "Lock";
	public static final String OPERATE_TYPE_UNLOCK = "Unlock";
	
	public static final String LOGIN_OPERATER_DEVICE = "device";
	public static final String LOGIN_OPERATER_SYSTEM = "system";
	
	public static final String STATION_STATUS_ONLINE = "online";
	public static final String STATION_STATUS_OFFLINE = "offline";

	public static final String MAP_KEY_STATUS_ATTRIBUTE = "StatusAttribute";
	
	public static final byte STATION_RESPONSE_SUCCESS = 0x00;
	public static final byte STATION_RESPONSE_FAILURE = 0x01;
	public static final byte STATION_RESPONSE_DUPLICATE = 0x02;
	
	
	public static final String ROUTE_HEAD_SUCCESS = "Success";
	public static final String ROUTE_HEAD_DIRTY = "Dirty";
	public static final String ROUTE_HEAD_INVALID = "Invalid";
	public static final String ROUTE_HEAD_STATUS = "Status";
	public static final String ROUTE_HEAD_LOGIN = "Login";
	public static final String ROUTE_HEAD_LOGOUT = "Logout";
	public static final String ROUTE_HEAD_TIMING = "Timing";
	public static final String ROUTE_HEAD_HEARTBEAT = "Heartbeat";
	public static final String ROUTE_HEAD_SUPPLEMENT = "Supplement";
	public static final String ROUTE_HEAD_QUERY = "Query";
	public static final String ROUTE_HEAD_SETUP = "Setup";
	public static final String ROUTE_HEAD_PARAMETER = "Parameter";
	public static final String ROUTE_HEAD_CONTROL = "Control";
	
	public static final String PARAMETER_TYPE_WORD = "word";
	public static final String PARAMETER_TYPE_DWORD = "dword";
	public static final String PARAMETER_TYPE_BYTE = "byte";
	public static final String PARAMETER_TYPE_BIT = "bit";
	public static final String PARAMETER_TYPE_BIT2 = "bit2";
	public static final String PARAMETER_TYPE_BIT4 = "bit4";
	public static final String PARAMETER_TYPE_CAN = "can";
	public static final String PARAMETER_TYPE_REAL = "real";
	
	public static final String VEHICLE_HBASE_LABEL_TABLE = "TableName";
	public static final String VEHICLE_HBASE_LABEL_ROWKEY = "RowKey";
	public static final String VEHICLE_HBASE_LABEL_FAMILY = "Family";
	public static final String VEHICLE_HBASE_LABEL_QUALIFY = "Qualify";
	public static final String VEHICLE_HBASE_LABEL_VALUE = "Value";
	public static final String VEHICLE_HBASE_LABEL_LONGITUDE = "Longitude";
	public static final String VEHICLE_HBASE_LABEL_LATITUDE = "Latitude";
	
	public static final String VEHICLE_HBASE_TABLENAME_STATUS = "vstatus";
	public static final String VEHICLE_HBASE_FAMILY_STATUS = "sf";
	public static final String VEHICLE_HBASE_FAMILY_POSITION = "pf";
	
	public static final long VEHICLE_STATUS_STEP_DEFAULT = 10000;

	public static final int ALARM_OCCUR = 1;
	public static final int ALARM_CLEAN = 2;
	public static final String DEFAULT_ALARM_PROCESS_USER = "Auto";
}
