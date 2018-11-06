package kr.or.ddit.ibatis.config;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapClientFatory {
	private static SqlMapClient smc;
	
	static {
		try {
			Reader rd= Resources.getResourceAsReader("kr/or/ddit/ibatis//config/SqlMapConfig.xml");
			smc= SqlMapClientBuilder.buildSqlMapClient(rd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlMapClient getSqlMapClient(){
		return smc;
	}
}
