package com.mantenimiento.spring.controladores.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.authorizeRequests()

				.antMatchers("/css/**", "/imagenes/**", "/js/**", "/", "/principal", "/home",
						"/inicio", "/logeo",
						"/login", "/api/**")
				.permitAll()

				.antMatchers("/vehiculos/listarTodo").hasAnyRole("ADMIN", "LECTOR",
						"CREADOR", "EDITOR", "DEPURADOR")
				.antMatchers("/vehiculos/nuevo").hasAnyRole("ADMIN", "CREADOR")
				.antMatchers("/vehiculos/guardar").hasAnyRole("ADMIN", "CREADOR", "EDITOR")
				.antMatchers("/vehiculos/actualizar/**").hasAnyRole("ADMIN", "EDITOR")
				.antMatchers("/vehiculo/eliminar/**").hasAnyRole("ADMIN", "DEPURADOR")

				.antMatchers("/reservas/listarTodo").hasAnyRole("ADMIN", "LECTOR", "CREADOR",
						"EDITOR", "DEPURADOR")
				.antMatchers("/reservas/nuevo").hasAnyRole("ADMIN", "CREADOR")
				.antMatchers("/reservas/guardar").hasAnyRole("ADMIN", "CREADOR", "EDITOR")
				.antMatchers("/reservas/actualizar/**").hasAnyRole("ADMIN", "EDITOR")
				.antMatchers("/reservas/eliminar/**").hasAnyRole("ADMIN", "DEPURADOR")

				.antMatchers("/clientes/listarTodo").hasAnyRole("ADMIN", "LECTOR", "CREADOR",
						"EDITOR", "DEPURADOR")
				.antMatchers("/clientes/nuevo").hasAnyRole("ADMIN", "CREADOR")
				.antMatchers("/clientes/guardar").hasAnyRole("ADMIN", "CREADOR", "EDITOR")
				.antMatchers("/clientes/actualizar/**").hasAnyRole("ADMIN", "EDITOR")
				.antMatchers("/clientes/eliminar/**").hasAnyRole("ADMIN", "DEPURADOR")

				.anyRequest().authenticated()

				.and().formLogin().loginPage("/login").defaultSuccessUrl("/bienvenida",
						true)
				.permitAll()
				.and().logout()
				.permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("admin")).roles("ADMIN").and()
				.withUser("keisy").password(encoder.encode("keisy")).roles("LECTOR").and()
				.withUser("anabel").password(encoder.encode("anabel")).roles("LECTOR", "CREADOR").and()
				.withUser("rousse").password(encoder.encode("rousse")).roles("LECTOR", "EDITOR").and()
				.withUser("valentino").password(encoder.encode("valentino")).roles("LECTOR", "EDITOR", "CREADOR").and()
				.withUser("andrew").password(encoder.encode("andrew")).roles("LECTOR", "EDITOR", "CREADOR", "DEPURADOR")
				.and();
	}

}
