package org.apache.solomax;

import java.util.Arrays;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.flow.RedirectToUrlException;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

		add(new Form<String>("form1")
			.add(new Button("oauthBtn1").add(new Label("label", "Ajax Redirect to Google"))
				.add(new AjaxEventBehavior("click") {
					private static final long serialVersionUID = 1L;

					@Override
					protected void onEvent(AjaxRequestTarget target) {
						throw new RedirectToUrlException("https://google.com");
					}
			}))
			.add(new Button("oauthBtn2") {
				private static final long serialVersionUID = 1L;

				@Override
				public void onSubmit() {
					throw new RedirectToUrlException("https://google.com");
				}
			}.add(new Label("label", "Submit Redirect to Google")))
		);
		StatelessForm<String> form2 = new StatelessForm<>("form2");
		form2.add(new ListView<String>("list", Arrays.asList("https://google.com?q=1", "https://google.com?q=2", "https://google.com?q=3")) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem<String> item) {
				item.add(new Button("oauthBtn") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onSubmit() {
						throw new RedirectToUrlException(item.getModelObject());
					}
				}.add(new Label("label", "Submit Redirect to " + item.getModelObject())));
			}
		});
		add(form2);
	}
}
