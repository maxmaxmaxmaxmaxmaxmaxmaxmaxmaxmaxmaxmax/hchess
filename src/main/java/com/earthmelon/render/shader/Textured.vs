#version 450 core

in vec3 position;
in vec2 texCoords;
in vec3 normal;

out vec2 pass_texCoords;
out vec3 pass_position;
out vec3 pass_normal;

uniform mat4 ppmatrix;

void main(void){
	gl_Position = vec4(position, 1.0) * ppmatrix;
	pass_position = vec3(gl_Position.x, gl_Position.y, gl_Position.z);

	pass_texCoords = texCoords;
	pass_normal = normal;
}