package com.globe_sh.cloudplatform.common.bean;

import com.globe_sh.cloudplatform.common.util.Bcc;
import com.globe_sh.cloudplatform.common.util.ByteArrayUtil;
import com.globe_sh.cloudplatform.common.util.StaticMethod;
import com.globe_sh.cloudplatform.common.util.StaticVariable;

import io.netty.channel.socket.SocketChannel;

public class DataPackage {

	private String clientId;
	
	private byte[] sourceData;	
	
	private byte[] targetData;
	
	private boolean valid;
	
	private SocketChannel socketChannel;
	
	public DataPackage() {
		
	}
	
	public DataPackage(String clientId, byte[] sourceData, SocketChannel socketChannel) {
		this.clientId = clientId;
		this.sourceData = sourceData;
		this.socketChannel = socketChannel;
	}
	
	public DataPackage(byte[] targetData) {
		this.targetData = targetData;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	public byte[] getSourceData() {
		return sourceData;
	}

	public void setSourceData(byte[] sourceData) {
		this.sourceData = sourceData;
	}

	public byte[] getTargetData() {
		return targetData;
	}

	public void setTargetData(byte[] targetData) {
		this.targetData = targetData;
	}

	public SocketChannel getSocketChannel() {
		return socketChannel;
	}

	public void setSocketChannel(SocketChannel socketChannel) {
		this.socketChannel = socketChannel;
	}

	/***
	 * 北端数据组包
	 * 组包结构：0x24,0x25,客户端id,原始包,0x25,0x24
	 * ***/
	public void pack() {
		this.valid = false;
		if(StaticMethod.isNull(this.clientId) || StaticMethod.isNull(sourceData) || 
				this.clientId.length() != StaticMethod.PACK_CONNECT_ID_LENGTH || 
				sourceData.length < StaticMethod.PACK_PACKAGE_CONTROL_LENGTH) {
			return;
		}
		
		try {
			short dataLength = ByteArrayUtil.getShortLowEnd(sourceData, StaticVariable.PROTOCOL_CONTROL_DATALENGTH_START);
			int sourceLength = sourceData.length;
			if((sourceLength - StaticVariable.PROTOCOL_CONTROL_LENGTH) == dataLength && 
					sourceData[0] == 0x23 && sourceData[1] == 0x23 /* && Bcc.getBccWithoutEnd(sourceData) == sourceData[sourceLength-1] */) {
				byte[] idBytes = StaticMethod.string2Ascii(this.clientId);
				int packageLength = idBytes.length + sourceLength + 4;
				byte[] packData = new byte[packageLength];
				
				packData[0] = StaticMethod.PACK_DATA_OUTSIDE;
				packData[1] = StaticMethod.PACK_DATA_INSIDE;
				packData[packageLength - 2] = StaticMethod.PACK_DATA_INSIDE;
				packData[packageLength - 1] = StaticMethod.PACK_DATA_OUTSIDE;
				System.arraycopy(idBytes, 0, packData, 2, idBytes.length);
				System.arraycopy(sourceData, 0, packData, idBytes.length + 2, sourceData.length);
				this.targetData = packData;
				this.valid = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void unpack() {
		this.valid = false;
		try {
			if(StaticMethod.isNull(targetData) || targetData.length < 65) {
				return;
			}
			int packageLength = targetData.length;
			int originaLength = targetData.length - 64;
			byte[] originalData = new byte[originaLength];
			if(targetData[0] == StaticMethod.PACK_DATA_OUTSIDE && targetData[1] == StaticMethod.PACK_DATA_INSIDE && 
					targetData[packageLength -2] == StaticMethod.PACK_DATA_INSIDE && targetData[packageLength -1] == StaticMethod.PACK_DATA_OUTSIDE) {
				this.clientId = StaticMethod.ascii2String(targetData, 2, StaticMethod.PACK_CONNECT_ID_LENGTH);
				System.arraycopy(targetData, 62, originalData, 0, originaLength);
				this.sourceData = originalData;
				this.valid = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
