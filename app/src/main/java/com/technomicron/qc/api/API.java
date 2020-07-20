package com.technomicron.qc.api;

import com.technomicron.qc.api.pojo.CombatAttack;
import com.technomicron.qc.api.pojo.GetAlertList;
import com.technomicron.qc.api.pojo.GetCombatList;
import com.technomicron.qc.api.pojo.GetFreelancerList;
import com.technomicron.qc.api.pojo.GetVaultBalance;
import com.technomicron.qc.api.pojo.HireFreelancer;
import com.technomicron.qc.api.pojo.PostFcmID;
import com.technomicron.qc.api.pojo.PostPlayer;
import com.technomicron.qc.api.pojo.PostBuildingPurchase;
import com.technomicron.qc.api.pojo.PostBuildingSell;
import com.technomicron.qc.api.pojo.PostBuildingUpgrade;
import com.technomicron.qc.api.pojo.GetPlayerBuildings;
import com.technomicron.qc.api.pojo.GetPlayerDetails;
import com.technomicron.qc.api.pojo.GetOrdnance;
import com.technomicron.qc.api.pojo.PostOrdnance;
import com.technomicron.qc.api.pojo.PostVault;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {


	@POST("users")
	Call<PostPlayer> createPlayer(@Body PostPlayer postPlayer);

	@GET("users/{token}")
	Call<PostPlayer> getPlayer(@Path("token") String token);

	@GET("users/{token}")
	Call<PostPlayer> getPlayerSync(@Path("token") String token);

	@POST("users/vault/withdraw")
	Call<PostVault> vaultWithdraw(@Body PostVault vD);

	@POST("users/vault/deposit")
	Call<PostVault> vaultDeposit(@Body PostVault vD);

	@GET("users/vault/{token}")
	Call<GetVaultBalance> getVaultBalance(@Path("token") String token);


	@GET("users/details/{token}")
	Call<GetPlayerDetails> getPlayerDetails(@Path("token") String token);

	@GET("buildings/{token}")
	Call<GetPlayerBuildings> getPlayerBuildings(@Path("token") String token);

	@GET("ordnance/{token}")
	Call<List<GetOrdnance>> getPlayerOrdnance(@Path("token") String token);


	@POST("ordnance/buy")
	Call<PostOrdnance> playerOrdnancePurchase(@Body PostOrdnance pOBuy);

	@POST("buildings/upgrade")
	Call<PostBuildingUpgrade> playerBuildingUpgrade(@Body PostBuildingUpgrade pBU);

	@POST("buildings/purchase")
	Call<PostBuildingPurchase> playerBuildingPurchase(@Body PostBuildingPurchase pBP);

	@POST("buildings/sell")
	Call<PostBuildingSell> playerBuildingSell(@Body PostBuildingSell pBS);


	@POST("users/fcmid")
	Call<PostFcmID> updateFcmID(@Body PostFcmID uFI);

	@GET("users/alerts/{token}")
	Call<List<GetAlertList>> getAlertList(@Path("token") String token);


	@GET("combat/{token}")
	Call<List<GetCombatList>> getCombatList(@Path("token") String token);

	@POST("combat/attack")
	Call<CombatAttack> CombatAttack(@Body CombatAttack cA);

	@GET("freelancers/{token}")
	Call<List<GetFreelancerList>> getFreelancerList(@Path("token") String token);

	@GET("freelancers/hire/{token}")
	Call<List<GetFreelancerList>> getHireableFreelancerList(@Path("token") String token);

	@POST("freelancers/hire")
	Call<HireFreelancer> HireFreelancer(@Body HireFreelancer hF);

	@POST("freelancers/discharge")
	Call<HireFreelancer> dischargeFreelancer(@Body HireFreelancer hF);


}
