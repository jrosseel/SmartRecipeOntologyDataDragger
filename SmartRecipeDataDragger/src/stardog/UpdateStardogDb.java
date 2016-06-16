package stardog;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.openrdf.rio.RDFFormat;

import com.complexible.stardog.StardogException;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;

public class UpdateStardogDb {

	public boolean updateFromFile(String fileLoc) {
		Connection aCon = null;
		
		try {
			aCon = _createServerConnectionConfig().connect();
			
			aCon.begin();
			aCon.add()
				.io()
				.format(RDFFormat.RDFXML)
				.stream(new FileInputStream(fileLoc));
			aCon.commit();
			
			return true;
		}
		catch(StardogException | FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			if(aCon != null)
				aCon.close();
		}
	}
	
	private ConnectionConfiguration _createServerConnectionConfig()
	{
		StarDogConnectionConfig conf = new StarDogConnectionConfig();
		
		return ConnectionConfiguration
					.to(conf.DB_NAME)
					.credentials(conf.ADMIN_NAME, conf.ADMIN_PASS)
					.server(conf.SERVER_ADDRESS);
	}
}
