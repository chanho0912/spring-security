<template>
  <v-container>
    <v-row align="center" justify="center">
      <v-col cols="12" sm="10">
        <v-card class="elevation-6 mt-10">
          <v-window v-model="step">
            <v-window-item :value="1">
              <v-row>
                <v-col cols="12" sm="6">
                  <v-card-text class="mt-12">
                    <h4 class="text-center">Login in to Your Account</h4>
                    <h6 class="text-center green--text">Log in to your account so you can continue building
                      <br>and editing your onboarding flows
                    </h6>
                    <v-row align="center" justify="center">
                      <v-col cols="12" sm="8">
                        <v-text-field label="Email" outlined dense color="blue" autocomplete="false" class="mt-16">
                        </v-text-field>
                        <v-text-field label="Password" outlined dense color="blue" autocomplete="false" type="password">
                        </v-text-field>
                        <v-row>
                          <v-col cols="12" sm="7">
                            <v-checkbox label="Remember me" class="mt-n1" color="blue"></v-checkbox>
                          </v-col>
                          <v-col cols="12" sm="5">
                            <span class="caption blue--text">Forget password</span>
                          </v-col>
                        </v-row>
                        <v-btn color="blue" dark block tile>Login in</v-btn>
                        <h5 class="text-center green--text mt-4 mb-3">Or Log in using</h5>
                        <v-btn color="green" block depressed class="mb-3" @click="continueWithNaver">
                          <v-icon color="white">fa-solid fa-n</v-icon>
                          <span style="margin-left: 10px">Continue with naver</span>
                        </v-btn>
                        <v-btn color="yellow" block depressed class="mb-3">
                          <v-icon color="white">fa-solid fa-k</v-icon>
                          <span style="margin-left: 10px">Continue with kakao</span>
                        </v-btn>
                        <v-btn color="grey" block depressed class="mb-3" @click="continueWithGoogle">
                          <v-icon color="white">fa-solid fa-g</v-icon>
                          <span style="margin-left: 10px">Continue with google</span>
                        </v-btn>
                        <v-btn color="blue" block depressed class="mb-10">
                          <v-icon color="white">fa-solid fa-f</v-icon>
                          <span style="margin-left: 10px">Continue with facebook</span>
                        </v-btn>
                      </v-col>
                    </v-row>
                  </v-card-text>
                </v-col>
                <v-col cols="12" sm="6" class="blue rounded-bl-xl">
                  <div style="text-align: center; padding: 180px 0;">
                    <v-card-text class="white--text">
                      <h3 class="text-center">Don't Have an Account Yet?</h3>
                      <h6 class="text-center">
                        Let's get you all set up so you can start creating your first<br>
                        onboarding experience
                      </h6>
                    </v-card-text>
                    <div class="text-center">
                      <v-btn tile outlined dark @click="step++">SIGN UP</v-btn>
                    </div>
                  </div>
                </v-col>
              </v-row>
            </v-window-item>
            <v-window-item :value="2">
              <v-row>
                <v-col cols="12" sm="6" class="blue rounded-br-xl">
                  <div style="text-align: center; padding: 180px 0;">
                    <v-card-text class="white--text">
                      <h3 class="text-center">Already Signed up?</h3>
                      <h6 class="text-center">SingLog in to your account so you can continue building and<br>
                        editing your onboarding flows
                      </h6>
                    </v-card-text>
                    <div class="text-center">
                      <v-btn tile outlined dark @click="step--">Log in</v-btn>
                    </div>
                  </div>
                </v-col>
                <v-col cols="12" sm="6">
                  <v-card-text class="mt-12">
                    <h4 class="text-center">Sign up for an Account</h4>
                    <h6 class="text-center green--text">
                      Let's get you all set up so you can start creation your<br>
                      first onboarding experience
                    </h6>
                    <v-row align="center" justify="center">
                      <v-col cols="12" sm="8">
                        <v-row>
                          <v-col cols="12" sm="6">
                            <v-text-field label="First Name" outlined dense color="blue" autocomplete="false" class="mt-4">
                            </v-text-field>
                          </v-col>
                          <v-col cols="12" sm="6">
                            <v-text-field label="First Name" outlined dense color="blue" autocomplete="false" class="mt-4">
                            </v-text-field>
                          </v-col>
                        </v-row>
                        <v-text-field label="Email" outlined dense color="blue" autocomplete="false">
                        </v-text-field>
                        <v-text-field label="Password" outlined dense color="blue" type="password">
                        </v-text-field>
                        <v-row>
                          <v-col cols="12" sm="7">
                            <v-checkbox label="I Accept AAE" class="mt-n1" color="blue"></v-checkbox>
                          </v-col>
                          <v-col cols="12" sm="5">
                            <span class="caption blue--text ml-n4">Terms & Conditions</span>
                          </v-col>
                        </v-row>
                        <v-btn color="blue" dark block tile> Sign up</v-btn>
                        <h5 class="text-center grey--text mt-4 mb-3">Or Sign up using</h5>
                        <div class="d-flex justify-space-between align-center mx-10 mb-11">
                          <v-btn depressed outlined color="grey">
                            <v-icon color="red">fab fa-google</v-icon>
                          </v-btn>
                          <v-btn depressed outlined color="grey">
                            <v-icon color="blue">fab fa-facebook-f</v-icon>
                          </v-btn>
                          <v-btn depressed outlined color="grey">
                            <v-icon color="light-blue lighten-3">fab fa-twitter</v-icon>
                          </v-btn>
                        </div>
                      </v-col>
                    </v-row>
                  </v-card-text>
                </v-col>
              </v-row>
            </v-window-item>
          </v-window>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios';

export default {
  data: function () {
    return {
      step: 1,
    }
  },
  props: {
    source: String
  },
  methods: {
    continueWithGoogle: () => {
      axios.get("http://localhost:8080/oauth2/authorization/google")
           .then(response => console.log(response));
    },
    continueWithNaver: () => {
      window.location.href = "https://nid.naver.com/oauth2.0/authorize" +
                             "?response_type=code" +
                             "&client_id=jo6jVfje8daqvslF5fVn" +
                             "&scope=name%20email" +
                             "&state=cUDtCgob0HjzTnjgTP68nz_qpYVIguMx2G4xPZHf2NY%3D" +
                             "&redirect_uri=http://localhost:8080/login/oauth2/code/naver"
    }
  }

}
</script>

<style scoped>
.v-application .rounded-bl-xl {
  border-bottom-left-radius: 300px !important;
}

.v-application .rounded-br-xl {
  border-bottom-right-radius: 300px !important;
}

</style>
