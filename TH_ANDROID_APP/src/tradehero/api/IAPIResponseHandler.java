package tradehero.api;

import tradehero.core.net.RestError;

public interface IAPIResponseHandler<T>
{
	public void onSuccess(T obj);
	public void onFailure(RestError error);
}
