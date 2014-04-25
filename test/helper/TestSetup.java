package helper;
import static org.mockito.Mockito.*;

import java.io.File;
import java.util.Collections;

import play.Configuration;
import play.GlobalSettings;
import play.mvc.Http.Context;
import play.mvc.Http.Request;
import play.mvc.Http.Flash;
import play.api.mvc.RequestHeader;


public class TestSetup {

	public static Context testHttpContext() {
		
		final RequestHeader mockRequestHeader = mock(RequestHeader.class);
		when(mockRequestHeader.host()).thenReturn("localhost");
		final Request mockRequest = mock(Request.class);
		when(mockRequest.host()).thenReturn("localhost");
		
		final Context ctx = mock(Context.class);
		when(ctx._requestHeader()).thenReturn(mock(play.api.mvc.RequestHeader.class));
		when(ctx.request()).thenReturn(mockRequest);
		when(ctx.flash()).thenReturn(new Flash(Collections.<String, String>emptyMap()));
//		when(ctx.lang()).thenReturn(play.i18n.Lang.forCode("en"));
		
		return ctx;
	}

	public static GlobalSettings testGlobalSettings() {
		return new GlobalSettings() {

			@Override
			public Configuration onLoadConfig(Configuration config, File path,
					ClassLoader cl) {
				// todo: customize as required
				return super.onLoadConfig(config, path, cl);
			}
			
		};
	}
	
}
