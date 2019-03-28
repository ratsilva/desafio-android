package br.com.desafioandroid.model.retrofit

import br.com.desafioandroid.model.room.PullRequest
import br.com.desafioandroid.model.room.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitRepository {

    @GET("search/repositories")
    fun getRepositories(@Query("q") q: String,
                        @Query("sort") sort: String,
                        @Query("page") page: Int): Call<ResponseGsonRepo>

    @GET("repos/{owner}/{repo}/pulls")
    fun getPulls(@Path("owner") owner: String,
                 @Path("repo") repo: String,
                 @Query("page") page: Int): Call<List<PullRequest>>

}

fun searchRepos(
    api: RetrofitRepository,
    q: String,
    sort: String,
    page: Int,
    onSuccess: (repos: List<Repo>) -> Unit,
    onError: (error: String) -> Unit) {

    var callAPI: Call<ResponseGsonRepo> = api.getRepositories(q, sort, page)

    callAPI.enqueue(object : Callback<ResponseGsonRepo> {

        override fun onFailure(call: Call<ResponseGsonRepo>?, t: Throwable) {
            onError(t.message ?: "Erro ao carregar informações")
        }

        override fun onResponse(call: Call<ResponseGsonRepo>?, response: Response<ResponseGsonRepo>) {

            if (response.isSuccessful) {
                val repos = response.body()?.items ?: emptyList()

                for (repo in repos){
                    repo.page = page
                    repo.login_owner = repo.repo_owner!!.owner_login
                }

                onSuccess(repos)
            } else {
                onError(response.errorBody()?.string() ?: "Erro ao carregar informações")
            }

        }

    })
}

fun searchPullRequests(
    api: RetrofitRepository,
    owner: String,
    repo: String,
    page: Int,
    onSuccess: (pulls: List<PullRequest>) -> Unit,
    onError: (error: String) -> Unit) {

    var callAPI: Call<List<PullRequest>> = api.getPulls(owner, repo, page)

    callAPI.enqueue(object : Callback<List<PullRequest>> {

        override fun onFailure(call: Call<List<PullRequest>>?, t: Throwable) {
            onError(t.message ?: "Erro ao carregar informações")
        }

        override fun onResponse(call: Call<List<PullRequest>>?, response: Response<List<PullRequest>>) {

            if (response.isSuccessful) {

                val pulls = response.body() ?: emptyList()

                for (pull in pulls){
                    pull.pullrequest_loginOwner = owner
                    pull.pullrequest_nameRepo = repo
                }

                onSuccess(pulls)
            } else {

                onError(response.errorBody()?.string() ?: "Erro ao carregar informações")
            }

        }

    })
}